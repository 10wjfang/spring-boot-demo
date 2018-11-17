package com.fang.domain.result;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/11/8 11:55
 * @Modified by:
 */
public class ResponseBean {
    private String code;
    private String msg;

    public ResponseBean(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseBean(ExceptionMsg exceptionMsg) {
        this.code = exceptionMsg.getCode();
        this.msg = exceptionMsg.getMsg();
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
