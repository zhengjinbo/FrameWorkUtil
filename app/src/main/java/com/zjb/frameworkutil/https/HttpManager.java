package com.zjb.frameworkutil.https;

import android.content.Context;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.zjb.frameworkutil.BuildConfig;
import com.zjb.frameworkutil.base.BaseApi;
import com.zjb.frameworkutil.impl.HttpOnNextListener;
import com.zjb.frameworkutil.utils.LogUtil;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * http交互处理类
 * Created by jb.
 */
public class HttpManager {
    private volatile static HttpManager INSTANCE;

    //构造方法私有
    private HttpManager() {
    }

    //获取单例
    public static HttpManager getInstance() {
        if (INSTANCE == null) {
            synchronized (HttpManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpManager();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 处理http请求
     *
     * @param basePar 封装的请求数据
     */
    public void doHttpDeal(Context context, BaseApi basePar) {
        //手动创建一个OkHttpClient并设置超时时间缓存等设置
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(basePar.getConnectionTime(), TimeUnit.SECONDS);
        builder.readTimeout(5, TimeUnit.SECONDS);
        builder.writeTimeout(5, TimeUnit.SECONDS);
        builder.addInterceptor(new CookieInterceptor(context, basePar.isCache(), basePar.getUrl()));
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(getHttpLoggingInterceptor());
        }
        /*创建retrofit对象*/
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(basePar.getBaseUrl())
                .build();
        /*rx处理*/
        ProgressSubscriber subscriber = new ProgressSubscriber(basePar);
        Observable observable = basePar.getObservable(retrofit)

                /*失败后的retry配置*/
                .retryWhen(new RetryWhenNetworkException(context, basePar.getRetryCount(), basePar.getRetryDelay(), basePar.getRetryIncreaseDelay()))
                /*生命周期管理*/
                .compose(basePar.getRxAppCompatActivity().bindUntilEvent(ActivityEvent.PAUSE))
                /*http请求线程*/
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                /*回调线程*/
                .observeOn(AndroidSchedulers.mainThread())
                /*结果判断*/
                .map(basePar);

        /*数据回调*/
        observable.subscribe(subscriber);

        /*链接式对象返回*/
//        SoftReference<HttpOnNextListener> httpOnNextListener = basePar.getListener();
        HttpOnNextListener httpOnNextListener2 = basePar.getListener();
        if(httpOnNextListener2 != null){
            httpOnNextListener2.onNext(observable);
        }
//        if (httpOnNextListener != null && httpOnNextListener.get() != null) {
//            httpOnNextListener.get().onNext(observable);
//        }else {
//            LogUtils.e("null ..........");
//        }
    }


    /**
     * 日志输出
     * 自行判定是否添加
     *
     * @return
     */
    private HttpLoggingInterceptor getHttpLoggingInterceptor() {
        //日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtil.e(message);
            }
        });
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }
}
