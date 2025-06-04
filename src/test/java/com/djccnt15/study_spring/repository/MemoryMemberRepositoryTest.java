package com.djccnt15.study_spring.repository;


import com.djccnt15.study_spring.db.model.MemberEntity;
import com.djccnt15.study_spring.db.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {
    
    MemoryMemberRepository repository = new MemoryMemberRepository();
    
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }
    
    @Test
    public void save() {
        var member = MemberEntity.builder().name("test2").build();
        repository.save(member);
        var result = repository.findById(member.getId()).orElseThrow();
        assertThat(member).isEqualTo(result);
    }
    
    @Test
    public void findByName() {
        var member1 = MemberEntity.builder().name("member1").build();
        repository.save(member1);
        var member2 = MemberEntity.builder().name("member2").build();
        repository.save(member2);
        
        var result1 = repository.findByName("member1").orElseThrow();
        assertThat(result1).isEqualTo(member1);
        var result2 = repository.findByName("member2").orElseThrow();
        assertThat(result2).isNotEqualTo(member1);
    }
    
    @Test
    public void findAll() {
        var member1 = MemberEntity.builder().name("member1").build();
        repository.save(member1);
        var member2 = MemberEntity.builder().name("member2").build();
        repository.save(member2);
        
        var result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
