package org.javaboy.vhr.utils;

public class StatusUtils {

    private String message;

    private Integer status;

    private Object object;

    public Object getObject() {
        return object;
    }

    public StatusUtils() {
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public StatusUtils(Integer status) {
        this.status = status;
    }

    public StatusUtils(String message, Integer status) {
        this.message = message;
        this.status = status;
    }

    public StatusUtils(String message, Integer status, Object object) {
        this.message = message;
        this.status = status;
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StatusUtils(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

//    /* 登录成功的话返回状态码 还有用户对象*/
//    private static StatusUtils login_ok(String message,Integer status, Object object) {
//        return new StatusUtils(message, status, object);
//    }
//
    /*登录失败返回消息提示加状态码
    public static StatusUtils login_error(String message,Integer status) {
        return new StatusUtils(message, status);
    }
    */
}
