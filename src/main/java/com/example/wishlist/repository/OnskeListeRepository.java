package com.example.wishlist.repository;


import com.example.wishlist.models.Bruger;
import com.example.wishlist.models.OnskeListe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

@Repository
public class OnskeListeRepository {

    private final JdbcTemplate jdbcTemplate;

    public OnskeListeRepository(@Value("${spring.datasource.url}")
                                String dbUrl,
                                @Value("${spring.datasource.username}")
                                String username,
                                @Value("${spring.datasource.password}")
                                String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(dbUrl);             // Use injected dbUrl directly
        dataSource.setUsername(username);     // Use injected username directly
        dataSource.setPassword(password);     // Use injected password directly
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        this.jdbcTemplate = new JdbcTemplate(dataSource);

    }

    public List<OnskeListe> getAllOnskeListe(Bruger bruger) {
        return new ArrayList<>();
    }

    public OnskeListe findOnskeListeByName(String name) {
        return new OnskeListe(name);
    }

    public void createOnskeListe() {

    }

    public void addOnske() {

    }

    public void deleteOnske() {

    }

    public void updateOnskeListe() {

    }
}


