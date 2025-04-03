package com.example.wishlist.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OnskeListeRowMapper implements RowMapper<OnskeListe> {
    @Override
    public OnskeListe mapRow(ResultSet rs, int rowNum) throws SQLException {
        String name = rs.getString("wishList_name");
        int id = rs.getInt("id");
        //String ID = rs.getString("id");
        List<Onske> Onsker = new ArrayList<>();
        return new OnskeListe(id, name, Onsker);
    }

}
