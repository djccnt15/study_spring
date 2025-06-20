package com.djccnt15.study_spring.prepost;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * NetworkClient example
 */
@Slf4j
@Setter
public class PrePostAnnotationBean {

    private String url;
    
    public PrePostAnnotationBean() {
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
    
    @PostConstruct
    public void init() throws Exception {
        log.info("PrePostAnnotationBean.init");
        connect();
        call("initialized connect message");
    }
    
    @PreDestroy
    public void close() throws Exception {
        log.info("PrePostAnnotationBean.close");
        disconnect();
    }
}
