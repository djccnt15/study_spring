package com.djccnt15.study_spring.db.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberEntity {
    
    private Long id;
    
    private String name;
}
