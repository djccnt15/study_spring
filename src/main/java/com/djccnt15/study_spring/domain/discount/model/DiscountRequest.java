package com.djccnt15.study_spring.domain.discount.model;

import com.djccnt15.study_spring.domain.discount.enums.MemberGrade;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DiscountRequest {

    private MemberGrade grade;
    
    private Integer price;
    
    private Integer amount;
}
