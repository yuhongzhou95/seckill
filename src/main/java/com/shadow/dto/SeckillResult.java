package com.shadow.dto;

import java.io.Serializable;

public class SeckillResult<T> implements Serializable {
    private boolean state;
    private T data;
    private String msg;

    public SeckillResult(boolean state, T data) {
        this.state = state;
        this.data = data;
    }

    public SeckillResult(boolean state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
