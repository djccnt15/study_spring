package com.djccnt15.study_spring.domain.member.service;

import com.djccnt15.study_spring.db.model.MemberEntity;
import com.djccnt15.study_spring.db.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {
    
    MemberService service;
    MemoryMemberRepository repository;
    
    @BeforeEach
    public void beforeEach() {
        repository = new MemoryMemberRepository();
        service = new MemberService(repository);
    }
    
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }
    
    @Test
    void join() {
        // given
        var member = MemberEntity.builder().name("test").build();
        
        // when
        var savedId = service.join(member);
        
        // then
        var findMember = service.findOne(savedId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    
    @Test
    void dupedMemberException() {
        // given
        var member1 = MemberEntity.builder().name("test").build();
        var member2 = MemberEntity.builder().name("test").build();
        
        // when
        service.join(member1);
        
        // then
        /*
        try {
            memberService.join(member2);
            org.junit.jupiter.api.Assertions.fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("already exist member name");
        }
        */
        var e = assertThrows(
            IllegalStateException.class,
            () -> service.join(member2)
        );
        assertThat(e.getMessage()).isEqualTo("already exist member name");
    }
    
    @Test
    void findMembers() {
    }
    
    @Test
    void findOne() {
    }
}