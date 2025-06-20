package com.djccnt15.study_spring.domain.discount.model;

import com.djccnt15.study_spring.domain.discount.enums.MemberGradeEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DiscountResponse {
    
    private MemberGradeEnum grade;
    
    private Integer originalPrice;
    
    private Integer discount;
    
    private Integer total;
}
