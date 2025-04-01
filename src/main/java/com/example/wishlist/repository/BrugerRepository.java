package com.example.wishlist.repository;

import com.example.wishlist.models.Bruger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BrugerRepository {
    private final JdbcTemplate jdbcTemplate;

    public BrugerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void gemBruger(Bruger bruger) {
        String indsætBrugerSql = """
                INSERT INTO USERS (USERNAME, PASSWORD)
                VALUES(?,?)""";
        try {
            jdbcTemplate.update(indsætBrugerSql, bruger.getUserName(), bruger.getPassword());

        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("En bruger med navnet '" + bruger.getUserName() + "' eksisterer allerede.");
        }
    }


    public boolean logInd(String userName, String password) {
        String tjekLogindSql = """
                SELECT COUNT(*) > 0 FROM users WHERE username = ? AND password = ?;
                """;
        try {
           Boolean userExist = jdbcTemplate.queryForObject(tjekLogindSql, Boolean.class, userName , password);
           return Boolean.TRUE.equals(userExist);

        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Fejl ved login", e);
        }
    }

    public void opdaterProfil(Bruger gammelBruger, Bruger opdateretBruger) {
        String opdaterBrugerSql = """
                UPDATE USERS
                SET USERNAME=?, PASSWORD=?
                WHERE USERNAME=? AND PASSWORD=?
                """;
        try {
            jdbcTemplate.update(opdaterBrugerSql, opdateretBruger.getUserName(), opdateretBruger.getPassword(),
                    gammelBruger.getUserName(), gammelBruger.getPassword());

        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("En bruger med navnet '" + opdateretBruger.getUserName() + "' eksisterer allerede.");
        }

    }

    public void sletBruger(String userName) {
        String sql = "DELETE FROM USERS WHERE USERNAME = ?";
        try {
            jdbcTemplate.update(sql, userName);
        } catch (DataIntegrityViolationException e){
            throw new IllegalArgumentException("En bruger med navnet '" + userName + "' eksisterer allerede.");

        }
    }


    private static class BrugerRowMapper implements RowMapper<Bruger> {
        @Override
        public Bruger mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Bruger(rs.getString("USERNAME"), rs.getString("PASSWORD"));

        }
    }
}