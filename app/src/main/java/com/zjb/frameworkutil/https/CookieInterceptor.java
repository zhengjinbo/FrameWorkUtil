package com.zjb.frameworkutil.https;


import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.zjb.frameworkutil.config.Constant;
import com.zjb.frameworkutil.utils.CookieDbUtil;
import com.zjb.frameworkutil.utils.SPUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;


/**
 * gson持久化截取保存数据
 * Created by WZG on 2016/10/20.
 */
public class CookieInterceptor implements Interceptor {
    private final Context context;
    private CookieDbUtil dbUtil;
    /*是否缓存标识*/
    private boolean cache;
    /*url*/
    private String url;


    public CookieInterceptor(Context context, boolean cache, String url) {
        dbUtil = CookieDbUtil.getInstance();
        this.context = context;
        this.url = url;
        this.cache = cache;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        //配置request
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();

        //添加token到header
        requestBuilder.addHeader("AccessToken", SPUtil.getString(context, Constant.token, ""));


        Response.Builder responseBuilder = chain.proceed(requestBuilder.build()).newBuilder();
        Response response = responseBuilder.build();


        if (cache) {
            ResponseBody body = response.body();
            BufferedSource source = body.source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();
            Charset charset = Charset.defaultCharset();
            MediaType contentType = body.contentType();

            if (contentType != null) {
                charset = contentType.charset(charset);
            }
            String bodyString = buffer.clone().readString(charset);
            CookieResulte resulte = dbUtil.queryCookieBy(url);
            long time = System.currentTimeMillis();
            /*保存和更新本地数据*/
            if (resulte == null) {
                resulte = new CookieResulte(url, bodyString, time);
                dbUtil.saveCookie(resulte);
            } else {
                resulte.setResulte(bodyString);
                resulte.setTime(time);
                dbUtil.updateCookie(resulte);
            }
        }
//        ResponseBody responseBody = response.body();
//        BufferedSource source = responseBody.source();
//        source.request(Long.MAX_VALUE); // Buffer the entire body.
//        Buffer buffer = source.buffer();
//        Charset charset = UTF8;
//        MediaType contentType = responseBody.contentType();
//        if (contentType != null) {
//            charset = contentType.charset(UTF8);
//        }
//
//        String bodyString = buffer.clone().readString(charset);
//        Map<String, String> map = JsonUtils.jsonToMap(bodyString);
//        int errorCode = Integer.parseInt(map.get("errorCode"));
//        String errorMsg = map.get("errorMsg");
//        Intent intent = new Intent(context, LoginActivity.class);

        return response;
    }


}
