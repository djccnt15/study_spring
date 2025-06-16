package com.djccnt15.study_spring.domain.discount.model;

import com.djccnt15.study_spring.domain.discount.enums.DiscountPolicyEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StrategyDiscountRequest extends DiscountRequest {
    
    private DiscountPolicyEnum discountPolicy;
}
