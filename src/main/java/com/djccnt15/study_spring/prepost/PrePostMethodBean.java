package com.djccnt15.study_spring.prepost;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * NetworkClient example
 */
@Slf4j
@Setter
public class PrePostMethodBean {

    private String url;
    
    public PrePostMethodBean() {
        log.info("constructor called | url = {}", url);
    }
    
    public void connect() {
        log.info("connect: {}", url);
    }
    
    public void call(String message) {
        log.info("call: {}, message = {}", url, message);
    }
    
    public void disconnect() {
        log.info("close {}", url);
    }
    
    public void init() throws Exception {
        log.info("PrePostMethodBean.init");
        connect();
        call("initialized connect message");
    }
    
    public void close() throws Exception {
        log.info("PrePostMethodBean.close");
        disconnect();
    }
}
