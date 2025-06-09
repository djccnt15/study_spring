package com.djccnt15.study_spring.config;

import com.djccnt15.study_spring.aop.TimeTraceAop;
import com.djccnt15.study_spring.db.repository.MemberRepository;
import com.djccnt15.study_spring.domain.member.service.MemberServiceBean;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {
    
    private final MemberRepository memberRepository;
    
    @Bean
    public MemberServiceBean memberServiceBean() {
        return new MemberServiceBean(memberRepository);
    }
    
    // @Bean
    // public MemberRepository memberRepositoryBean() {
        // return new MemoryMemberRepositoryBean();
        // return new JdbcMemberRepository(dataSource);
        // return new JdbcTemplateMemberRepository(dataSource);z
        // return new JpaMemberRepository(em);
    // }
}
