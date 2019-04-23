package com.port.constant;

/**
 * 请求状态枚举类
 */
public enum State {
    SUCCESS("success"),
    FAIL("fail"),
    ;
    String state;
    State(String state){
        this.state = state;
    }
    public String getState(){
        return state;
    }
}
