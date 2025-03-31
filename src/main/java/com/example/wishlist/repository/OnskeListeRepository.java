package com.example.wishlist.repository;


import com.example.wishlist.models.Bruger;
import com.example.wishlist.models.Onske;
import com.example.wishlist.models.OnskeListe;
import com.example.wishlist.models.OnskeListeRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OnskeListeRepository {

    private final JdbcTemplate jdbcTemplate;
    private final OnskeListeRowMapper onskeListeRowMapper = new OnskeListeRowMapper();

    public OnskeListeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<OnskeListe> faOnskeListeFraBruger(String username) {
        String sql = "SELECT w.id, w.wishList_name " +
                "FROM wishLists w " +
                "JOIN users u ON w.user_id = u.id " +
                "WHERE u.username = ?";
        return jdbcTemplate.query(sql, onskeListeRowMapper, username);
    }

    public List<OnskeListe> faAllOnskeListe() {
        String sql = "SELECT w.wishList_name " +
                "FROM wishlists w";
        return jdbcTemplate.query(sql, onskeListeRowMapper);
    }


    public OnskeListe findOnskeListeByName(String name) {
        return new OnskeListe(name);
    }

    public void createOnskeListe(String name, String username) {

        String userIdQuery = "SELECT id FROM users WHERE username = ?";
        Integer userId = jdbcTemplate.queryForObject(userIdQuery, Integer.class, username);

        String sql = "INSERT INTO wishlists (user_id, wishList_name) VALUES (?, ?)";
        jdbcTemplate.update(sql, userId, name);
    }

    public void addOnske() {

    }

    public void deleteOnske() {

    }

    public void updateOnskeListe() {

    }
}


