package com.example.wishlist.repository;

import com.example.wishlist.models.Bruger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BrugerRepository extends com.example.wishlist.repository.Repository {

    List<Bruger> gemteBrugere = new ArrayList<>();

    public BrugerRepository(JdbcTemplate jdbc){
        super(jdbc);
        makeBrugere();
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
