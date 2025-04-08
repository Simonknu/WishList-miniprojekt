package com.example.wishlist.repository;


import com.example.wishlist.models.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OnskeListeRepository {

    private final JdbcTemplate jdbcTemplate;
    private final OnskeListeRowMapper onskeListeRowMapper = new OnskeListeRowMapper();
    private final OnskeRowMapper onskeRowMapper = new OnskeRowMapper();


    //----------------------------------CONSTRUCTOR---------------------------------------------------------------
    public OnskeListeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //---------------------------------------GETTER METHOD--------------------------------------------------------------
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    //---------------------------------ROW MAPPERS-----------------------------------------------
    public class OnskeListeRowMapper implements RowMapper<OnskeListe> {
        @Override
        public OnskeListe mapRow(ResultSet rs, int rowNum) throws SQLException {
            String name = rs.getString("wishList_name");

            List<Onske> onsker = faOnsker(name);

            // Return the Attraction object with tags
            return new OnskeListe(name, onsker);
        }
    }

    public class OnskeRowMapper implements RowMapper<Onske> {

        @Override
        public Onske mapRow(ResultSet rs, int rowNum) throws SQLException {
            String name = rs.getString("wish_name");
            String description = rs.getString("description");
            String link = rs.getString("link");

            return new Onske(name, description, link);
        }
    }

//------------------------------------------------------------------------------------------

    public List<OnskeListe> faOnskeListeFraBruger(String username) {
        String sql = "SELECT w.wishList_name " +
                "FROM wishLists w " +
                "JOIN users u ON w.user_id = u.id " +
                "WHERE u.username = ?";

        return jdbcTemplate.query(sql, onskeListeRowMapper, username);
    }

    public List<Onske> faOnsker(String name) {
        String sql = "SELECT w.wish_name " +
                "FROM wishes w " +
                "JOIN wishLists wL ON w.wishList_id = wL.id" +
                " WHERE wL.wishList_name = ?";

        return jdbcTemplate.query(sql, onskeRowMapper, name);
    }

    public List<OnskeListe> faAllOnskeListe() {
        String sql = "SELECT w.wishList_name " +
                "FROM wishlists w";
        return jdbcTemplate.query(sql, onskeListeRowMapper);
    }


    public OnskeListe findOnskeListeMedNavn(String name) {
        String sql = "SELECT w.wishList_name FROM wishLists w WHERE wishList_name = ?";

        List<Onske> onsker = faOnsker(name);
        try {
            return jdbcTemplate.queryForObject(sql, onskeListeRowMapper, name);
        } catch (EmptyResultDataAccessException e) {
            return null; // return null when no wishlist is found
        }
    }

    public void opretOnskeListe(String name, String username) {

        String userIdQuery = "SELECT id FROM users WHERE username = ?";
        Integer userId = jdbcTemplate.queryForObject(userIdQuery, Integer.class, username);

        String sql = "INSERT INTO wishlists (user_id, wishList_name) VALUES (?, ?)";
        jdbcTemplate.update(sql, userId, name);
    }

    public void tilfojeOnske() {

    }

    public void sletOnskeListe(String name) {
        String sql = "DELETE FROM wishLists WHERE wishList_name = ?";

        jdbcTemplate.update(sql, name);
    }

    public void redigerOnskeListe(String oldName, String newName) {
        String sql = "UPDATE wishLists SET wishList_name = ? WHERE wishList_name = ?";

        jdbcTemplate.update(sql, newName, oldName);
    }


    public boolean gentagetNavn(String name) {
        if (findOnskeListeMedNavn(name) == null) {
            return false;
        }
        return true;
    }

}


