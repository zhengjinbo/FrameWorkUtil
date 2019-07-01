package com.zjb.frameworkutil.https;


import com.zjb.frameworkutil.entity.SendIdentifyCodeBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by jb.
 */

public interface HttpService {

    //发送短信验证码
    @FormUrlEncoded
    @POST("WebApi/User/SendIdentifyCode")
    Observable<SendIdentifyCodeBean> sendIdentifyCode(@Field("SmsType") String smsType, @Field("Phone") String phone, @Field("areaCode") String areaCode);


//    @GET("/WebApi/HomePage/GetRecommendProductList")
//    Observable<HomeBean> getRecommendProductList(@Query("deviceId") String deviceId, @Query("pageIndex") int pageIndex, @Query("pageSize") int pageSize);


//    //根据AccessToken来获取用户基本信息(AccessToken通过请求头信息进行传递)
//    @POST("WebApi/User/GetUserInfo")
//    Observable<UserInfoBean> getUserInfo();

}
