package com.zjb.frameworkutil.activity

import android.view.View
import com.zjb.frameworkutil.R
import com.zjb.frameworkutil.api.SendIdentifyCodeApi
import com.zjb.frameworkutil.base.BaseActivity
import com.zjb.frameworkutil.entity.SendIdentifyCodeBean
import com.zjb.frameworkutil.https.HttpManager
import com.zjb.frameworkutil.https.RetryWhenNetworkException
import com.zjb.frameworkutil.impl.HttpOnNextListener
import com.zjb.frameworkutil.utils.AppUtil
import com.zjb.frameworkutil.utils.LogUtil
import kotlinx.android.synthetic.main.activity_kotlin.*

/**
 * @author ZJB
 * @date 2019/7/1 10:20
 */

class KotlinActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_kotlin


    override fun initUI() {
        button.setOnClickListener(View.OnClickListener {
            sendSmsCode("13489332169")
        })
    }

    override fun initData() {
        when (2) {
            0 -> LogUtil.e("------------------0")
            1 -> LogUtil.e("------------------1")
            2 -> LogUtil.e("------------------2")
            3 -> LogUtil.e("------------------3")
            4 -> LogUtil.e("------------------4")
        }
    }

    private fun sendSmsCode(phoneNum: String) {
        val sendIdentifyCodeApi = SendIdentifyCodeApi(this)
        sendIdentifyCodeApi.phone = phoneNum
        sendIdentifyCodeApi.smsType = "1"
        sendIdentifyCodeApi.areaCode = "86"
        sendIdentifyCodeApi.listener = object : HttpOnNextListener<SendIdentifyCodeBean>() {
            override fun onNext(sendIdentifyCodeBean: SendIdentifyCodeBean): RetryWhenNetworkException.Wrapper? {
                LogUtil.e("kotlinActivity", AppUtil.getGson().toJson(sendIdentifyCodeBean))
                return null
            }
        }
        HttpManager.getInstance().doHttpDeal(this, sendIdentifyCodeApi)
    }

}