package com.djccnt15.study_spring.db.repository;

import com.djccnt15.study_spring.db.model.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<MemberEntity, Long>, MemberRepository {

    @Override
    Optional<MemberEntity> findByName(String name);
}
