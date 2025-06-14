package com.djccnt15.study_spring.domain.discount.service;

import com.djccnt15.study_spring.domain.discount.annotation.FixedDiscountPolicy;
import com.djccnt15.study_spring.domain.discount.discounter.Discounter;
import com.djccnt15.study_spring.domain.discount.model.DiscountRequest;
import com.djccnt15.study_spring.domain.discount.model.DiscountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoWiredDiscountService {
    
    private final Discounter discounter;
    
    @Autowired
    public AutoWiredDiscountService(@FixedDiscountPolicy Discounter discounter) {
        this.discounter = discounter;
    }
    
    public DiscountResponse discount(DiscountRequest request) {
        var total = request.getPrice() - discounter.discount(request);
        return DiscountResponse.builder()
            .grade(request.getGrade())
            .originalPrice(request.getPrice())
            .discount(request.getAmount())
            .total(total)
            .build();
    }
}
