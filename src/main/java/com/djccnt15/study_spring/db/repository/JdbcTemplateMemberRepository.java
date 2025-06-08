package com.djccnt15.study_spring.db.repository;

import com.djccnt15.study_spring.db.model.MemberEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository {
    
    private final JdbcTemplate jdbcTemplate;
    
    // @Autowired  // 생성자가 1개일 때는 @Autowired 생략 가능
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override
    public MemberEntity save(MemberEntity member) {
        var jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
        
        var params = new HashMap<String, Object>();
        params.put("name", member.getName());
        
        var key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params));
        member.setId(key.longValue());
        return member;
    }
    
    @Override
    public Optional<MemberEntity> findById(Long id) {
        var result = jdbcTemplate.query("SELECT * FROM MEMBER WHERE ID = ?", memberRowMapper(), id);
        return result.stream().findFirst();
    }
    
    @Override
    public Optional<MemberEntity> findByName(String name) {
        var result = jdbcTemplate.query("SELECT * FROM MEMBER WHERE NAME = ?", memberRowMapper(), name);
        return result.stream().findFirst();
    }
    
    @Override
    public List<MemberEntity> findAll() {
        return jdbcTemplate.query("SELECT * FROM MEMBER", memberRowMapper());
    }
    
    private RowMapper<MemberEntity> memberRowMapper() {
        return (rs, rowNum) -> MemberEntity.builder()
            .id(rs.getLong("id"))
            .name(rs.getString("name"))
            .build();
    }
}
