package com.djccnt15.study_spring.domain.discount.controller;

import com.djccnt15.study_spring.domain.discount.model.DiscountRequest;
import com.djccnt15.study_spring.domain.discount.model.DiscountResponse;
import com.djccnt15.study_spring.domain.discount.service.AutoWiredDiscountService;
import com.djccnt15.study_spring.domain.discount.service.LombokDiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "discount")
@RequiredArgsConstructor
public class DiscountController {
    
    private final AutoWiredDiscountService autoWiredDiscountService;
    private final LombokDiscountService lombokDiscountService;
    
    @PostMapping(path = "auto-wired")
    public DiscountResponse autoWiredService(DiscountRequest request) {
        return autoWiredDiscountService.discount(request);
    }
    
    @PostMapping(path = "lombok")
    public DiscountResponse lombokService(DiscountRequest request) {
        return lombokDiscountService.discount(request);
    }
}
