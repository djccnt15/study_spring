package com.djccnt15.study_spring.domain.discount.discounter;

import com.djccnt15.study_spring.domain.discount.annotation.RateDiscountPolicy;
import com.djccnt15.study_spring.domain.discount.enums.MemberGrade;
import com.djccnt15.study_spring.domain.discount.model.DiscountRequest;
import org.springframework.stereotype.Component;

@Component
@RateDiscountPolicy
public class RateDiscounter implements Discounter {
    
    @Override
    public Integer discount(DiscountRequest request) {
        if (request.getGrade() == MemberGrade.VIP) return request.getPrice() * request.getAmount() / 100;
        return 0;
    }
}
