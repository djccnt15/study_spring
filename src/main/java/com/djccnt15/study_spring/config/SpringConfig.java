package com.djccnt15.study_spring.config;

import com.djccnt15.study_spring.db.repository.JdbcMemberRepository;
import com.djccnt15.study_spring.db.repository.MemberRepository;
import com.djccnt15.study_spring.domain.member.service.MemberServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    
    @Autowired
    private DataSource dataSource;
    
    @Bean
    public MemberServiceBean memberServiceBean() {
        return new MemberServiceBean(memberRepositoryBean());
    }
    
    @Bean
    public MemberRepository memberRepositoryBean() {
        // return new MemoryMemberRepositoryBean();
        return new JdbcMemberRepository(dataSource);
    }
}
