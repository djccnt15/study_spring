package com.djccnt15.study_spring.domain.discount.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiscountPolicyEnum {
    
    FIXED("fixedDiscounter"),
    RATE("rateDiscounter"),
    ;
    
    private final String discounterName;
}
