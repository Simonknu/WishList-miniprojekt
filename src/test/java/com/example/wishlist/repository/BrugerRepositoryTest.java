package com.example.wishlist.repository;

import com.example.wishlist.models.Bruger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"classpath:h2init.sql"}
)

@Transactional
@Rollback(true)
public class BrugerRepositoryTest {

    @Autowired
    private BrugerRepository brugerRepository;

    @Test
    public void testBrugerLogind() throws SQLException{
        Bruger brugerTrue = new Bruger("alice","hashed_password1");
        Bruger brugerFalse = new Bruger("awijf","awpfkoajsiggao29ri");

        assertTrue(brugerRepository.logInd(brugerTrue.getUserName(), brugerTrue.getPassword()));
        assertFalse(brugerRepository.logInd(brugerFalse.getUserName(),brugerFalse.getPassword()));
    }

    @Test
    public void testNytBrugerLogind() throws SQLException{
        Bruger nyBruger = new Bruger("nyBruger","nyBrugerPassword");

        brugerRepository.gemBruger(nyBruger);

        assertTrue(brugerRepository.logInd(nyBruger.getUserName(),nyBruger.getPassword()));
    }

    @Test
    public void testSletBrugerLogind() throws SQLException{
        Bruger nyBruger = new Bruger("nyBruger","nyBrugerPassword");

        brugerRepository.gemBruger(nyBruger);
        assertTrue(brugerRepository.logInd(nyBruger.getUserName(),nyBruger.getPassword()));

        brugerRepository.sletBruger(nyBruger.getUserName());
        assertFalse(brugerRepository.logInd(nyBruger.getUserName(),nyBruger.getPassword()));

    }
}
