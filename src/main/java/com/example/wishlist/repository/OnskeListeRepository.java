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


    //----------------------------------CONSTRUCTOR---------------------------------------------------------------
    public OnskeListeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
//---------------------------------------GETTER METHOD--------------------------------------------------------------
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
//------------------------------------------------------------------------------------------
    public List<OnskeListe> faOnskeListeFraBruger(String username) {
        String sql = "SELECT w.wishList_name " +
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


    public OnskeListe findOnskeListeMedNavn (String name) {
        String sql = "SELECT w.wishList_name FROM wishLists w WHERE wishList_name = ?";

        return jdbcTemplate.queryForObject(sql,onskeListeRowMapper, name);
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

    public void redigerOnskeListe(String oldName, String newName, String newDescription, List<Onske> wishes) {
        String sql = "UPDATE wishList SET wishList_name = ?, description = ?, WHERE name = ?";

        jdbcTemplate.update(sql, newName, newDescription, oldName);
    }
}


