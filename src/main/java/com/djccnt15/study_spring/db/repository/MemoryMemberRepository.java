package com.djccnt15.study_spring.db.repository;

import com.djccnt15.study_spring.db.model.MemberEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {
    
    private static Map<Long, MemberEntity> store = new HashMap<>();
    private static long sequence = 0L;
    
    @Override
    public MemberEntity save(MemberEntity member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    
    @Override
    public Optional<MemberEntity> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
    
    @Override
    public Optional<MemberEntity> findByName(String name) {
        return store.values().stream()
            .filter(it -> it.getName().equals(name))
            .findFirst();
    }
    
    @Override
    public List<MemberEntity> findAll() {
        return new ArrayList<>(store.values());
    }
    
    public void clearStore() {
        store.clear();
    }
}
