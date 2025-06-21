package com.djccnt15.study_spring.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class SingletonTest {
    
    @Test
    void singletonBeanFind() {
        var ac = new AnnotationConfigApplicationContext(SingletonBean.class);
        
        var bean1 = ac.getBean(SingletonBean.class);
        log.info("bean1 = {}", bean1);
        
        var bean2 = ac.getBean(SingletonBean.class);
        log.info("bean2 = {}", bean2);
        
        assertThat(bean1).isSameAs(bean2);
        
        ac.close();
    }
    
    @Scope("singleton")
    static class SingletonBean {
        
        @PostConstruct
        public void init() {
            log.info("SingletonBean.init");
        }
        
        @PreDestroy
        public void destroy() {
            log.info("SingletonBean.destroy");
        }
    }
}
