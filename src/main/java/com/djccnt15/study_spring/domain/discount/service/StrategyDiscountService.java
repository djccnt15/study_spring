package com.djccnt15.study_spring.domain.discount.service;

import com.djccnt15.study_spring.domain.discount.discounter.Discounter;
import com.djccnt15.study_spring.domain.discount.enums.DiscountPolicyEnum;
import com.djccnt15.study_spring.domain.discount.model.DiscountResponse;
import com.djccnt15.study_spring.domain.discount.model.StrategyDiscountRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class StrategyDiscountService {
    
    private final Map<String, Discounter> policyMap;
    
    public DiscountResponse discount(StrategyDiscountRequest request) {
        var discounter = getDiscounter(request.getDiscountPolicy());
        var total = request.getPrice() - discounter.discount(request);
        return DiscountResponse.builder()
            .grade(request.getGrade())
            .originalPrice(request.getPrice())
            .discount(request.getAmount())
            .total(total)
            .build();
    }
    
    private Discounter getDiscounter(DiscountPolicyEnum discounterCode) {
        return policyMap.get(discounterCode.getDiscounterName());
    }
}
