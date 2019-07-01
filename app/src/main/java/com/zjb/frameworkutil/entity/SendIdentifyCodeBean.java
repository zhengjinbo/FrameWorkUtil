package com.zjb.frameworkutil.entity;

import java.io.Serializable;

/**
 * @author ZJB
 * @date 2019/7/1 9:56
 */
public class SendIdentifyCodeBean implements Serializable {


    /**
     * errorCode : 403
     * errorMsg : 该手机号已注册
     * returnData : null
     */

    private int errorCode;
    private String errorMsg;
    private Object returnData;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getReturnData() {
        return returnData;
    }

    public void setReturnData(Object returnData) {
        this.returnData = returnData;
    }

}
