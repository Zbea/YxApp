package com.yx.Pharmacy.base;

/**
 * Created time  2018/3/27 0027
 * @author : mcx
 * 类描述 : 
 */

public class BasisBean<T> {

    private String code;
    private String message;
    private String alertmsg;
    private String extention;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAlertmsg() {
        return alertmsg;
    }

    public void setAlertmsg(String alertmsg) {
        this.alertmsg = alertmsg;
    }

    public String getExtention() {
        return extention;
    }

    public void setExtention(String extention) {
        this.extention = extention;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
