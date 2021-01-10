package oslomet.webprog;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;

@Repository
public class  MotorvognRepository {

    @Autowired
    private JdbcTemplate db;
    private Logger logger = LoggerFactory.getLogger(MotorvognRepository.class);

    public boolean lagreEnMotorvogn(Motorvogn m) {
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
        try {
            //Har mapper for å få motorvogn over i en liste av motorvogn
            List<Motorvogn> alleMotorvogner = db.query(sql,new BeanPropertyRowMapper(Motorvogn.class));
            return alleMotorvogner;
        } catch(Exception e){
            logger.error("Feil i hentAlleMotorvogner: "+e);
            return null;
        }
     }

    public Motorvogn hentEnMotorvogn(int id){
        String sql = "SELECT * FROM Motorvogn WHERE id=?";
        try{
            Motorvogn enMotorvogn = db.queryForObject(sql, new Object[]{id},BeanPropertyRowMapper.newInstance(Motorvogn.class));
            return enMotorvogn;
        }
        catch(Exception e) {
            logger.error("Feil i hentEnKunde : " + e);
            return null;
        }
    }

    public boolean endreEnMotorvogn(Motorvogn m){
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
        try {
            db.update(sql,personnr);
            return true;
        } catch (Exception e){
            logger.error("Feil i slettEnMotorvogn: "+e);
            return false;
        }
    }

    public boolean slettAlleMotorvogner () {
        String sql = "DELETE FROM Motorvogn";
        try {
            db.update(sql);
            return true;
        } catch (Exception e){
            logger.error("Feil i slettAlleMotorvogner: "+e);
            return false;
        }
    }

    public List<Bil> hentAlleBiler(){
        String sql = "SELECT * FROM Bil";
        try {
            List<Bil> alleBiler = db.query(sql,new BeanPropertyRowMapper(Bil.class));
            return alleBiler;
        } catch (Exception e){
            logger.error("Feil i hentAlleBiler: "+e);
            return null;
        }
    }
    public boolean sjekkPassord(String passord, String hashPassord) {
        return BCrypt.checkpw(passord, hashPassord);
    }
    /**
     * I sql strengen vår skal vi bare sjekke om det finnes en rad som inneholder det brukernavnet og passordet
     * som vi får inn, så vi kan bare telle antall rader og får et svar. Så sjekker vi om funnetEnBruker er større
     * enn 0, det vil si at det finnes minst en rad med dette brukernavnet og passordet. Isåfall returnerer vi true,
     * hvis ikke så returnerer vi false. I catch gjør vi feilhåndteringen og returnerer også false.
     */
    public boolean loggInn(String brukernavn, String passord) {
        String sql = "SELECT * FROM Bruker WHERE brukernavn=?";
        try {
            List<Bruker> brukere = db.query(sql, new BeanPropertyRowMapper(Bruker.class), brukernavn);
            if (brukere != null) {
                if (sjekkPassord(passord, brukere.get(0).getPassord())) { //henter 1 elem i listen hvis flere brukere har samme brukernavn
                    return true;
                }
            }
            return false;
        } catch (Exception e){
            return false;
        }
    }
    private String krypterPassord(String passord){
        return BCrypt.hashpw(passord, BCrypt.gensalt(15));
    }

    public boolean krypterAllePassord(){
        String sql = "SELECT * FROM Bruker";
        String kryptertPassord; //skal holde det krypterte passordet
        try {
            List<Bruker> alleBrukere = db.query(sql, new BeanPropertyRowMapper(Bruker.class));
            //Lager en egen bruker-klasse slik at vi kan hente alle rader i brukertabellen som objekter
            for(Bruker b : alleBrukere){
                kryptertPassord = krypterPassord(b.getPassord()); //lager et kryptert passord for hver bruker
                sql = "UPDATE Bruker SET passord=? WHERE id=?"; //Her oppdaterer vi passordet som ligger i db
                db.update(sql, kryptertPassord, b.getId());
            }
            return true;
        } catch (Exception e){
            return false;
        }
    }
}