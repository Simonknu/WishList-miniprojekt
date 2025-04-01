package com.example.wishlist.repository;

import com.example.wishlist.models.Bruger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BrugerRepository {

    List<Bruger> gemteBrugere = new ArrayList<>();
    private final JdbcTemplate jdbcTemplate;

    public BrugerRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;

    }

    public void gemBruger(Bruger bruger) {
        gemteBrugere.add(bruger);
    }


    //dummykode
    public void getGemteBrugere() {
        for (Bruger bruger : gemteBrugere) {
            System.out.println(bruger);
        }
    }

    private void makeBrugere() {
        gemteBrugere.add(new Bruger("daniellaErSej", "daniella123"));
    }

    public boolean logInd(String userName, String password) {
        for (Bruger bruger : gemteBrugere) {
            if (bruger.getUserName().equals(userName) && bruger.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
