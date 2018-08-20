package com.gavin.system;

public enum PageCodeEnum {
    USER_ERROR(1000,"用户名或密码错误")
    ;
    private Integer code;
    private String msg;
    public static final String KEY="pageCode";

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    PageCodeEnum(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
