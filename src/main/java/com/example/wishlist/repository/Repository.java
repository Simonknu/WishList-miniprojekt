package com.example.wishlist.repository;

import org.springframework.jdbc.core.JdbcTemplate;


public class Repository {

    private final JdbcTemplate jdbc;

    public Repository (JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public JdbcTemplate getJdbc() {
        return jdbc;
    }
}
