package com.djccnt15.study_spring.domain.discount.service;

import com.djccnt15.study_spring.domain.discount.discounter.Discounter;
import com.djccnt15.study_spring.domain.discount.model.DiscountRequest;
import com.djccnt15.study_spring.domain.discount.model.DiscountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LombokDiscountService {
    
    private final Discounter fixedDiscounter;
    
    public DiscountResponse discount(DiscountRequest request) {
        var total = request.getPrice() - fixedDiscounter.discount(request);
        return DiscountResponse.builder()
            .grade(request.getGrade())
            .originalPrice(request.getPrice())
            .discount(request.getAmount())
            .total(total)
            .build();
    }
}
