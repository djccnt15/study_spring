package com.djccnt15.study_spring.domain.member.service;

import com.djccnt15.study_spring.db.model.MemberEntity;
import com.djccnt15.study_spring.db.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    
    private final MemberRepository memberRepository;
    
    @Autowired
    public MemberService(MemberRepository memoryMemberRepository) {
        this.memberRepository = memoryMemberRepository;
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
