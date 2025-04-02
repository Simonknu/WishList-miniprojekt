package com.example.wishlist.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OnskeRowMapper implements RowMapper<Onske> {

    @Override
    public Onske mapRow(ResultSet rs, int rowNum) throws SQLException {
        String name = rs.getString("wish_name");
        String desc = rs.getString("wish_description");
        String link = rs.getString("wish_link");
        return  new Onske();
    }

}
