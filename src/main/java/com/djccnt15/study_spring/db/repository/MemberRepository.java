package com.djccnt15.study_spring.db.repository;

import com.djccnt15.study_spring.db.model.MemberEntity;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    
    MemberEntity save(MemberEntity member);
    
    Optional<MemberEntity> findById(Long id);
    
    Optional<MemberEntity> findByName(String name);
    
    List<MemberEntity> findAll();
}
