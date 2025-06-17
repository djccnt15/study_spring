package com.djccnt15.study_spring.beaninfo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
public class AppContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    
    @Test
    @DisplayName("모든 빈 출력")
    void findAllBean() {
        var beanDefinitionNames = ac.getBeanDefinitionNames();
        for (var beanDefinitionName : beanDefinitionNames) {
            var bean = ac.getBean(beanDefinitionName);
            log.info(bean.toString());
        }
    }
    
    @Test
    @DisplayName("애플리케이션 빈 출력")
    void findAppBean() {
        var beanDefinitionNames = ac.getBeanDefinitionNames();
        for (var beanDefinitionName : beanDefinitionNames) {
            var beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                var bean = ac.getBean(beanDefinitionName);
                log.info(bean.toString());
            }
        }
    }
    
    @Configuration
    @ComponentScan(basePackages = "com.djccnt15.study_spring")
    static class AppConfig {}
}
