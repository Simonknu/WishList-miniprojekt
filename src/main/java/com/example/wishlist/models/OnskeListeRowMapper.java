package com.example.wishlist.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OnskeListeRowMapper implements RowMapper<OnskeListe> {
    @Override
    public OnskeListe mapRow(ResultSet rs, int rowNum) throws SQLException {
        String name = rs.getString("name");

        List<Onske> Onsker = new ArrayList<>();
        // Return the Attraction object with tags
        return new OnskeListe(name, Onsker);
    }

}
