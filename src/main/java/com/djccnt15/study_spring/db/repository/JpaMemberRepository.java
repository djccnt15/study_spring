package com.djccnt15.study_spring.db.repository;

import com.djccnt15.study_spring.db.model.MemberEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class JpaMemberRepository implements MemberRepository {
    
    private final EntityManager em;
    
    @Override
    public MemberEntity save(MemberEntity member) {
        em.persist(member);
        return member;
    }
    
    @Override
    public Optional<MemberEntity> findById(Long id) {
        var member = em.find(MemberEntity.class, id);
        return Optional.ofNullable(member);
    }
    
    @Override
    public Optional<MemberEntity> findByName(String name) {
        var result = em.createQuery("select m from MemberEntity m where m.name = :name", MemberEntity.class)
            .setParameter("name", name)
            .getResultList();
        return result.stream().findFirst();
    }
    
    @Override
    public List<MemberEntity> findAll() {
        return em.createQuery("select m from MemberEntity m", MemberEntity.class)
            .getResultList();
    }
}
