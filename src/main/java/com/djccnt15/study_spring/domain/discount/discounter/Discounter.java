package com.djccnt15.study_spring.domain.discount.discounter;

import com.djccnt15.study_spring.domain.discount.model.DiscountRequest;

public interface Discounter {
    
    Integer discount(DiscountRequest request);
}
