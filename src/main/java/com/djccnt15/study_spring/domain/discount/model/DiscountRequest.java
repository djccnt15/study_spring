package com.djccnt15.study_spring.domain.discount.model;

import com.djccnt15.study_spring.domain.discount.enums.MemberGradeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountRequest {

    private MemberGradeEnum grade;
    
    private Integer price;
    
    private Integer amount;
}
