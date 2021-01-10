package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

@Repository
public class BillettRepository {

    @Autowired
    private JdbcTemplate db;

    public void lagreBillett(Billett enBillett){
        String sql = "INSERT INTO Billett (fornavn, etternavn, telefonnr, epost, film, antall) VALUES(?,?,?,?,?,?)";
        db.update(sql, enBillett.getFornavn(), enBillett.getEtternavn(), enBillett.getTelefonnr(),
                enBillett.getEpost(), enBillett.getFilm(), enBillett.getAntall());
    }

    public List<Billett> hentAlle(){
        String sql = "SELECT * FROM Billett";
        List<Billett> alleBilletter = db.query(sql, new BeanPropertyRowMapper(Billett.class));
        return alleBilletter;
    }

    public void slettAlleBilletter(){
        String sql = "DELETE FROM Billett";
        db.update(sql);
    }

}
