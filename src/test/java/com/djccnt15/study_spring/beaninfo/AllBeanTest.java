package com.djccnt15.study_spring.beaninfo;

import com.djccnt15.study_spring.domain.discount.discounter.Discounter;
import com.djccnt15.study_spring.domain.discount.enums.MemberGradeEnum;
import com.djccnt15.study_spring.domain.discount.model.DiscountRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class AllBeanTest {

    @Test
    void findAllBean() {
        var ac = new AnnotationConfigApplicationContext(AppConfig.class, TestDiscountService.class);
        var discountService = ac.getBean(TestDiscountService.class);
        var request = new DiscountRequest(MemberGradeEnum.VIP, 1000, 10);
        
        var fixedDiscount = discountService.discount(request, "fixedDiscounter");
        assertThat(fixedDiscount).isEqualTo(10);
        
        var rateDiscount = discountService.discount(request, "rateDiscounter");
        assertThat(rateDiscount).isEqualTo(100);
    }
    
    static class TestDiscountService {
        private final Map<String, Discounter> policyMap;
        private final List<Discounter> policyList;
        
        @Autowired
        public TestDiscountService(Map<String, Discounter> policyMap, List<Discounter> policyList) {
            this.policyMap = policyMap;
            this.policyList = policyList;
            System.out.println(policyMap);
            System.out.println(policyList);
        }
        
        public Integer discount(DiscountRequest request, String discountCode) {
            var discounter = policyMap.get(discountCode);
            return discounter.discount(request);
        }
    }
    
    @Configuration
    @ComponentScan(basePackages = "com.djccnt15.study_spring")
    static class AppConfig {}
}
