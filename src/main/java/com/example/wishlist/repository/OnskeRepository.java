package com.example.wishlist.repository;

import com.example.wishlist.models.Onske;
import com.example.wishlist.models.OnskeRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OnskeRepository {

    private final JdbcTemplate jdbcTemplate;
    private OnskeRowMapper onskeRowMapper;

    public OnskeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Onske> getOnskerByWishlistID(int id)
    {
        String sql = "SELECT w.wish_name " +
                "FROM wishes w " +
                "JOIN wishlists u ON w.wishList_id = u.id " +
                "WHERE u.id = ?";

        return jdbcTemplate.query(sql, onskeRowMapper, id);
    }

    public void addToRepository(int ListID, Onske onske)
    {
        String sql = "INSERT INTO wishes (wishlist_id, wish_name, description, link) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, ListID, onske.getName(), onske.getDescription(), onske.getLink());
    }

    public void updateWish(Onske onskeOld, Onske onskeNew)
    {
        String sql = "UPDATE wishes SET wish_name = ?, description = ?, wish_name = ? WHERE id = ?";

        jdbcTemplate.update(sql, onskeNew.getName(), onskeNew.getDescription(), onskeNew.getLink(), onskeOld.getID());
    }

    public void deleteWish(int wishID)
    {
        String sql = "DELETE FROM wishes WHERE wishList_id = ?";

        jdbcTemplate.update(sql, wishID);
    }

}
