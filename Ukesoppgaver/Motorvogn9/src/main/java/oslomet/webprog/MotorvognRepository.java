package oslomet.webprog;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MotorvognRepository {

    @Autowired
    private JdbcTemplate db;

    private Logger logger = LoggerFactory.getLogger(MotorvognRepository.class);

    public boolean lagreMotorvogn(Motorvogn m) {
        String sql = "INSERT INTO Motorvogn (personnr,navn,adresse,kjennetegn,merke,type) VALUES(?,?,?,?,?,?)";
        try{
            db.update(sql,m.getPersonnr(),m.getNavn(),m.getAdresse(),m.getKjennetegn(),m.getMerke(),m.getType());
            return true;
        }
        catch(Exception e){
            logger.error("Feil i lagre motorvogn "+e);
            return false;
        }
    }

    public List<Motorvogn> hentAlleMotorvogner() {
        String sql = "SELECT * FROM Motorvogn";
        try{
            return db.query(sql,new BeanPropertyRowMapper(Motorvogn.class));
        }
        catch(Exception e){
            logger.error("Feil i hent alle motorvogner "+e);
            return null;
        }
     }

     public Motorvogn henteEnMotorvogn(int id){
         String sql = "SELECT * FROM Motorvogn WHERE id=?";
         try{
             List<Motorvogn> enMotorvogn  = db.query(sql,new BeanPropertyRowMapper(Motorvogn.class),id);
             return enMotorvogn.get(0);
         }
         catch(Exception e){
             logger.error("Feil i hent en motorvogn "+e);
             return null;
         }
     }

     public boolean endreMotorvogn(Motorvogn m){
         String sql = "UPDATE Motorvogn SET personnr=?, navn=?,adresse=?,kjennetegn=?,merke=?,type=? where id=?";
         try{
             db.update(sql,m.getPersonnr(),m.getNavn(),m.getAdresse(),m.getKjennetegn(),m.getMerke(),m.getType(),m.getId());
             return true;
         }
         catch(Exception e){
             logger.error("Feil i endre en motorvogn "+e);
             return false;
         }
     }

     public boolean slettEnMotorvogn(String personnr) {
        String sql = "DELETE FROM Motorvogn WHERE personnr=?";
        try{
            db.update(sql,personnr);
            return true;
        }
        catch(Exception e){
            logger.error("Feil i slett en motorvogn"+e);
            return false;
        }
    }

    public boolean slettAlleMotorvogner () {
        String sql = "DELETE FROM Motorvogn";
        try{
            db.update(sql);
            return true;
        }
        catch(Exception e){
            logger.error("Feil i slett alle motorvogner"+e);
            return false;
        }
    }

    public List<Bil> hentAlleBiler(){
        String sql = "SELECT * FROM Bil";
        try{
            return db.query(sql,new BeanPropertyRowMapper(Bil.class));
        }
        catch(Exception e){
            return null;
        }
    }


    private String krypterPassord(String passord){
        return BCrypt.hashpw(passord, BCrypt.gensalt(15));
    }

    private boolean sjekkPassord( String passord, String hashPassord){
        return BCrypt.checkpw(passord,hashPassord);
    }


    public boolean loggInn(String brukernavn,String passord){
        String sql = "SELECT * FROM Bruker WHERE brukernavn = ?";
        try{

            List<Bruker> brukere = db.query(sql,new BeanPropertyRowMapper(Bruker.class),brukernavn);
            // evt. flere med samme navn, tar første nedenfor (index = 0)

            if(brukere != null){
                if(sjekkPassord(passord,brukere.get(0).getPassord())){
                    return true;
                }
            }
            return false;
        }
        catch(Exception e){
            return false;
        }
    }

    public boolean krypterAllePassord(){
        String sql = "SELECT * from Bruker";
        String kryptertPassord;
        try{
            List<Bruker> alleBrukere= db.query(sql,new BeanPropertyRowMapper(Bruker.class));

            for (Bruker b : alleBrukere){

                kryptertPassord = krypterPassord(b.getPassord());

                sql = "UPDATE Bruker SET passord=? where id=?";
                db.update(sql,kryptertPassord,b.getId());
            }
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
