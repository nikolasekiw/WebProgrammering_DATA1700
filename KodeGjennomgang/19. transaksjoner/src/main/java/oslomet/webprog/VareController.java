package oslomet.webprog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;

@RestController
public class VareController {

    @Autowired
    private JdbcTemplate db;

    private Logger logger = LoggerFactory.getLogger(VareController.class);

    @PostMapping("/lagre")
    @Transactional
    public void lagreMelding(Ordre o) {
        String sql1 = "INSERT INTO Kunde (fornavn,etternavn,adresse) VALUES(?,?,?)";
        String sql2 = "INSERT INTO Ordre (KId,varenavn,pris) VALUES(?,?,?)";
        KeyHolder id = new GeneratedKeyHolder();
        try {
            db.update(con -> {
                PreparedStatement par = con.prepareStatement(sql1, new String[]{"KId"});
                par.setString(1, o.getFornavn());
                par.setString(2, o.getEtternavn());
                par.setString(3, o.getAdresse());
                return par;
            }, id);
            int kid = id.getKey().intValue();
            db.update(sql2,kid,o.getVarenavn(),o.getPris());
        }
        catch (Exception e) {
            logger.error("Feil i lagre ordre! " + e);
        }
    }
}
