package com.djccnt15.study_spring.beaninfo;

import com.djccnt15.study_spring.prepost.PrePostAnnotationBean;
import com.djccnt15.study_spring.prepost.PrePostMethodBean;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        var annotationBean = ac.getBean(PrePostAnnotationBean.class);
        var methodBean = ac.getBean(PrePostMethodBean.class);
        ac.close();
    }
    
    @Configuration
    static class LifeCycleConfig {
    
        @Bean
        public PrePostAnnotationBean annotationBean() {
            var prePostTestBean = new PrePostAnnotationBean();
            prePostTestBean.setUrl("http://test.dev/annotation");
            return prePostTestBean;
        }
        
        @Bean(initMethod = "init", destroyMethod = "close")
        public PrePostMethodBean methodBean() {
            var prePostTestBean = new PrePostMethodBean();
            prePostTestBean.setUrl("http://test.dev/method");
            return prePostTestBean;
        }
    }
}
