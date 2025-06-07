package com.djccnt15.study_spring.db.repository;

import com.djccnt15.study_spring.db.model.MemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class JdbcMemberRepository implements MemberRepository {
    
    private final DataSource dataSource;
    
    @Override
    public MemberEntity save(MemberEntity member) {
        var sql = "INSERT INTO MEMBER(NAME) VALUES(?)";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, member.getName());
            pstmt.executeUpdate();
            
            rs = pstmt.getGeneratedKeys();
            
            if (rs.next()) {
                member.setId(rs.getLong(1));
            } else {
                throw new SQLException("ID 조회 실패");
            }
            return member;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    
    @Override
    public Optional<MemberEntity> findById(Long id) {
        var sql = "SELECT * FROM MEMBER WHERE ID = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                var member = MemberEntity.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .build();
                return Optional.of(member);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    
    @Override
    public Optional<MemberEntity> findByName(String name) {
        var sql = "SELECT * FROM MEMBER WHERE NAME = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                var member = MemberEntity.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .build();
                return Optional.of(member);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    
    @Override
    public List<MemberEntity> findAll() {
        var sql = "SELECT * FROM MEMBER";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            
            rs = pstmt.executeQuery();
            
            var memberList = new ArrayList<MemberEntity>();
            while (rs.next()) {
                var member = MemberEntity.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .build();
                memberList.add(member);
            }
            return memberList;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
    
    /**
     * using datasource.getConnection() returns new connection from datasource not from connection pool
     */
    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }
    
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            log.error("", e);
        }
        
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            log.error("", e);
        }
        
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            log.error("", e);
        }
    }
    
    /**
     * returns connection to connection pool
     */
    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
