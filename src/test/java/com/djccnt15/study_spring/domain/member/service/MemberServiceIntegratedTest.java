package com.djccnt15.study_spring.domain.member.service;

import com.djccnt15.study_spring.db.model.MemberEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional  // rollback db after each test
class MemberServiceIntegratedTest {
    
    @Autowired MemberServiceBean memberServiceBean;
    
    @Test
    void join() {
        // given
        var member = MemberEntity.builder().name("test").build();
        
        // when
        var savedId = memberServiceBean.join(member);
        
        // then
        var findMember = memberServiceBean.findOne(savedId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    
    @Test
    void dupedMemberException() {
        // given
        var member1 = MemberEntity.builder().name("test").build();
        var member2 = MemberEntity.builder().name("test").build();
        
        // when
        memberServiceBean.join(member1);
        
        // then
        var e = assertThrows(
            IllegalStateException.class,
            () -> memberServiceBean.join(member2)
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