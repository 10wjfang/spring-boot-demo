package com.fang.domain.result;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/11/8 11:57
 * @Modified by:
 */
public enum ExceptionMsg {
    SUCCESS("000000", "成功"),
    FAILED("999999", "失败"),
    ParamError("000001", "参数错误"),
    PasswordError("000002", "密码错误")
    ;
    private String code;
    private String msg;

    ExceptionMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
