package com.example.wishlist.repository;

import com.example.wishlist.models.Bruger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BrugerRepository {

    List<Bruger> gemteBrugere = new ArrayList<>();

    public BrugerRepository() {
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

    public void opdaterProfil(Bruger bruger) {


        for (Bruger b : gemteBrugere) {
            if (bruger.getUserName().equals(b.getUserName()) && bruger.getPassword().equals(b.getPassword())) {

                b.setUserName(bruger.getUserName());
                b.setPassword(bruger.getPassword());

            }
        }

    }
}
