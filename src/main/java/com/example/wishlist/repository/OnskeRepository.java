package com.example.wishlist.repository;

import com.example.wishlist.models.Onske;
import com.example.wishlist.models.OnskeListe;
import com.example.wishlist.models.OnskeRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OnskeRepository {

    private final JdbcTemplate jdbcTemplate;
    private final OnskeRowMapper onskeRowMapper = new OnskeRowMapper();

    public OnskeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Onske> getOnskerByWishlistID(int onskeListeIdid) {
        String sql = "SELECT * FROM wishes WHERE wishList_id = ?";

        return jdbcTemplate.query(sql, onskeRowMapper, onskeListeIdid);
    }

    public void addToRepository(int ListID, Onske onske) {
        String sql = "INSERT INTO wishes (wishlist_id, wish_name, description, link) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, ListID, onske.getName(), onske.getDescription(), onske.getLink());
    }

    public void updateWish(int id, Onske onskeNew) {
        String sql = "UPDATE wishes SET wish_name = ?, description = ?, wish_name = ? WHERE id = ?";

        jdbcTemplate.update(sql, onskeNew.getName(), onskeNew.getDescription(), onskeNew.getLink(), id);
    }

    public void deleteWish(int wishID) {
        String sql = "DELETE FROM wishes WHERE wishList_id = ?";

        jdbcTemplate.update(sql, wishID);
    }

    public Onske getOnskeByID(int wishID) {
        String sql = "SELECT w.id FROM wishLists w WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, onskeRowMapper, wishID);
    }
}
