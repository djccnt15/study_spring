package com.djccnt15.study_spring.domain.discount.discounter;

import com.djccnt15.study_spring.domain.discount.annotation.FixedDiscountPolicy;
import com.djccnt15.study_spring.domain.discount.enums.MemberGradeEnum;
import com.djccnt15.study_spring.domain.discount.model.DiscountRequest;
import org.springframework.stereotype.Component;

@Component
@FixedDiscountPolicy
public class FixedDiscounter implements Discounter {
    
    @Override
    public Integer discount(DiscountRequest request) {
        if (request.getGrade() == MemberGradeEnum.VIP) return request.getAmount();
        return 0;
    }
}
