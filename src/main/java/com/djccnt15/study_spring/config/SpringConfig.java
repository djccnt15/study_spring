package com.djccnt15.study_spring.config;

import com.djccnt15.study_spring.db.repository.MemberRepository;
import com.djccnt15.study_spring.db.repository.MemoryMemberRepositoryBean;
import com.djccnt15.study_spring.domain.member.service.MemberServiceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    
    @Bean
    public MemberServiceBean memberServiceBean() {
        return new MemberServiceBean(memberRepositoryBean());
    }
    
    @Bean
    public MemberRepository memberRepositoryBean() {
        return new MemoryMemberRepositoryBean();
    }
}
