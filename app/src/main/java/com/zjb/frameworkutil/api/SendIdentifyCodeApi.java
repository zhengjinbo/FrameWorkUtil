package com.zjb.frameworkutil.api;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zjb.frameworkutil.base.BaseApi;
import com.zjb.frameworkutil.https.HttpService;
import com.zjb.frameworkutil.impl.HttpOnNextListener;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * 发送短信验证码
 * Created by jb on 2018/9/11.
 */
public class SendIdentifyCodeApi extends BaseApi {
    //（1：注册 2：找回密码 3：提现）
    private String smsType;
    private String phone;
    private String areaCode;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }


    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public SendIdentifyCodeApi(RxAppCompatActivity rxAppCompatActivity) {
        super(rxAppCompatActivity);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpService httpService = retrofit.create(HttpService.class);
        return httpService.sendIdentifyCode(getSmsType(), getPhone(),getAreaCode());
    }
}
