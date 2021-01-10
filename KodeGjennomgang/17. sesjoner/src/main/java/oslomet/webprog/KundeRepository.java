package oslomet.webprog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KundeRepository {

    @Autowired
    public JdbcTemplate db;

    Logger logger = LoggerFactory.getLogger(KundeRepository.class);

    public boolean lagreKunde(Kunde kunde) {
        String sql = "INSERT INTO Kunde (navn,adresse,passord) VALUES(?,?,?)";
        try{
            db.update(sql,kunde.getNavn(),kunde.getAdresse(),kunde.getPassord());
            return true;
        }
        catch(Exception e){
            logger.error("Feil i lagreKunde : "+e);
            return false;
        }
    }

    public List<Kunde> hentAlleKunder() {
        String sql = "SELECT * FROM Kunde";
        try{
            List<Kunde> alleKunder = db.query(sql,new BeanPropertyRowMapper(Kunde.class));
            return alleKunder;
        }
        catch (Exception e){
            logger.error("Feil i hentAlleKunder : "+e);
            return null;
        }
    }


    public Kunde hentEnKunde(int id) {
        String sql = "SELECT * FROM Kunde WHERE id=?";
        try{
            Kunde enKunde = db.queryForObject(sql, new Object[]{id},BeanPropertyRowMapper.newInstance(Kunde.class));
            return enKunde;
        }
        catch(Exception e) {
            logger.error("Feil i hentEnKunde : " + e);
            return null;
        }
    }

    public boolean endreEnKunde(Kunde kunde){
        String sql = "UPDATE Kunde SET navn=?,adresse=?,passord=? where id=?";
        try{
            db.update(sql,kunde.getNavn(),kunde.getAdresse(),kunde.getPassord(),kunde.getId());
            return true;
        }
        catch(Exception e){
            logger.error("Feil i endreEnKunde : "+e);
            return false;
        }
    }

    public boolean slettEnKunde(int id) {
        String sql = "DELETE FROM Kunde WHERE id=?";
        try {
            db.update(sql,id);
            return true;
        }
        catch(Exception e){
            logger.error("Feil i slettEnKunde : "+e);
            return false;
        }
    }

    public boolean slettAlleKunder () {
        String sql = "DELETE FROM Kunde";
        try {
            db.update(sql);
            return true;
        }
        catch(Exception e){
            logger.error("Feil i slettAlleKunder : "+e);
            return false;
        }
    }

    public boolean sjekkNavnOgPassord (Kunde kunde) {
        Object[] param = new Object[]{kunde.getNavn(),kunde.getPassord()};
        String sql = "SELECT COUNT(*) FROM Kunde WHERE navn=? AND passord=?";
        try{
            int antall = db.queryForObject(sql,param,Integer.class);
            if (antall>0){
                return true;
            }
            return false;
        }
        catch (Exception e){
            logger.error("Feil i sjekkNavnOgPassord : "+e);
            return false;
        }
    }
}
