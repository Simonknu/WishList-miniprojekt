package com.example.wishlist.repository;

import com.example.wishlist.models.Bruger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BrugerRepository {
    private final JdbcTemplate jdbcTemplate;

    public BrugerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void gemBruger(Bruger bruger) {
        String indsetBrugerSql = """
                INSERT INTO USERS (USERNAME, PASSWORD)
                VALUES(?,?)
                """;
        try {
            jdbcTemplate.update(indsetBrugerSql, bruger.getUserName(), bruger.getPassword());

        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("En bruger med navnet '" + bruger.getUserName() + "' eksisterer allerede.");
        }
    }

    @Value("${spring.datasource.url}")
    private String dbUrl;

    public boolean logInd(String userName, String password) {
        String sql;
        if (dbUrl.contains("mysql")) {
            sql = "SELECT COUNT(*) FROM USERS WHERE BINARY USERNAME = ? AND BINARY PASSWORD = ?";
        } else {
            sql = "SELECT COUNT(*) FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
        }

        try {
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userName, password);
            return count != null && count > 0;
        } catch (DataAccessException e) {
            throw new IllegalArgumentException("Fejl ved login", e);
        }
    }

    public void opdaterProfil(Bruger gammelBruger, Bruger opdateretBruger) {
        String opdaterBrugerSql = """
                UPDATE USERS
                SET USERNAME = ?, PASSWORD = ?
                WHERE BINARY USERNAME = ? AND BINARY PASSWORD = ?
                """;
        try {
            jdbcTemplate.update(opdaterBrugerSql, opdateretBruger.getUserName(), opdateretBruger.getPassword(),
                    gammelBruger.getUserName(), gammelBruger.getPassword());
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("En bruger med navnet '" + opdateretBruger.getUserName() + "' eksisterer allerede.");
        }
    }

    public void sletBruger(String userName) {
        String sql = "DELETE FROM USERS WHERE BINARY USERNAME = ?";
        try {
            jdbcTemplate.update(sql, userName);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Der opstod en fejl ved sletning af bruger " + e);
        }
    }

    public boolean tjekUsernameDup(String username) {
        String tjekUsernameSql = """
                SELECT COUNT(*) > 0 FROM USERS WHERE BINARY USERNAME = ?;
                """;
        try {
            Boolean userExist = jdbcTemplate.queryForObject(tjekUsernameSql, Boolean.class, username);
            return Boolean.TRUE.equals(userExist);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Fejl ved tjek af brugernavn " + e);
        }
    }
}
