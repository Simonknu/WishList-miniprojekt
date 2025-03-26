package com.example.wishlist.repository;

import com.example.wishlist.models.Bruger;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BrugerRepository {

    List<Bruger> gemteBrugere;

    public BrugerRepository(){
        makeBrugere();
    }

    public void saveBruger(Bruger bruger) {
        gemteBrugere.add(bruger);
    }



    //dummykode
    public void getGemteBrugere(){
        for (Bruger bruger :gemteBrugere){
            System.out.println(bruger);
        }
    }

    private void makeBrugere(){
        gemteBrugere.add(new Bruger("daniellaErSej","daniella123","Daniella"));
    }
}
