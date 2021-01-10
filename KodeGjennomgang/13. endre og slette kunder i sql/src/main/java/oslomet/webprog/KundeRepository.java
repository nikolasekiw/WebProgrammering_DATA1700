package oslomet.webprog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KundeRepository {

    @Autowired
    public JdbcTemplate db;

    public void lagreKunde(Kunde kunde) {
        String sql = "INSERT INTO Kunde (navn,adresse) VALUES(?,?)";
        db.update(sql,kunde.getNavn(),kunde.getAdresse());
    }

    public List<Kunde> hentAlleKunder() {
        String sql = "SELECT * FROM Kunde";
        List<Kunde> alleKunder = db.query(sql,new BeanPropertyRowMapper(Kunde.class));
        return alleKunder;
    }

    public Kunde hentEnKunde(int id) {
        Object[] param = new Object[1];
        param[0] = id;
        String sql = "SELECT * FROM Kunde WHERE id=?";
        Kunde enKunde = db.queryForObject(sql, param, BeanPropertyRowMapper.newInstance(Kunde.class));
        return enKunde;
    }

    public void endreEnKunde(Kunde kunde){
        String sql = "UPDATE Kunde SET navn=?,adresse=? where id=?";
        db.update(sql,kunde.getNavn(),kunde.getAdresse(),kunde.getId());
    }

    public void slettEnKunde(int id) {
        String sql = "DELETE FROM Kunde WHERE id=?";
        db.update(sql,id);
    }

    public void slettAlleKunder () {
        String sql = "DELETE FROM Kunde";
        db.update(sql);
    }
}
