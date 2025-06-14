package com.djccnt15.study_spring.domain.member.service;

import com.djccnt15.study_spring.db.model.MemberEntity;
import com.djccnt15.study_spring.db.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberServiceBean {
    
    private final MemberRepository memberRepository;
    
    public MemberServiceBean(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    
    /**
     * join member
     */
    public Long join(MemberEntity member) {
        validateDupedMember(member);
        memberRepository.save(member);
        return member.getId();
    }
    
    /**
     * get all members
     */
    public List<MemberEntity> findMembers() {
        return memberRepository.findAll();
    }
    
    public Optional<MemberEntity> findOne(Long id) {
        return memberRepository.findById(id);
    }
    
    private void validateDupedMember(MemberEntity member) {
        memberRepository.findByName(member.getName())
            .ifPresent(it -> {throw new IllegalStateException("already exist member name");});
    }
}
