package com.djccnt15.study_spring.config;

import com.djccnt15.study_spring.db.repository.JpaMemberRepository;
import com.djccnt15.study_spring.db.repository.MemberRepository;
import com.djccnt15.study_spring.domain.member.service.MemberServiceBean;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    
    @Autowired
    private EntityManager em;
    
    @Bean
    public MemberServiceBean memberServiceBean() {
        return new MemberServiceBean(memberRepositoryBean());
    }
    
    @Bean
    public MemberRepository memberRepositoryBean() {
        // return new MemoryMemberRepositoryBean();
        // return new JdbcMemberRepository(dataSource);
        // return new JdbcTemplateMemberRepository(dataSource);z
        return new JpaMemberRepository(em);
    }
}
