package com.fang.domain.result;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/11/8 11:56
 * @Modified by:
 */
public class ResponseDataBean extends ResponseBean {
    private Object data;

    public ResponseDataBean(String code, String msg, Object data) {
        super(code, msg);
        this.data = data;
    }

    public ResponseDataBean(ExceptionMsg exceptionMsg, Object data) {
        super(exceptionMsg);
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
