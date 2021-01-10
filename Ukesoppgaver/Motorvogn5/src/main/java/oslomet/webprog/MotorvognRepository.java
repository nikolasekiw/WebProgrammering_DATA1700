package oslomet.webprog;

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

    public void lagreMotorvogn(Motorvogn m) {
        String sql = "INSERT INTO Motorvogn (personnr,navn,adresse,kjennetegn,merke,type) VALUES(?,?,?,?,?,?)";
        db.update(sql,m.getPersonnr(),m.getNavn(),m.getAdresse(),m.getKjennetegn(),m.getMerke(),m.getType());
    }

    public List<Motorvogn> hentAlleMotorvogner() {
        String sql = "SELECT * FROM Motorvogn";
        return db.query(sql,new BeanPropertyRowMapper(Motorvogn.class));
     }


     public Motorvogn henteEnMotorvogn(int id){
         String sql = "SELECT * FROM Motorvogn WHERE id=?";
         List<Motorvogn> enMotorvogn  = db.query(sql,new BeanPropertyRowMapper(Motorvogn.class),id);
         return enMotorvogn.get(0);
     }

     public void endreMotorvogn(Motorvogn m){
         String sql = "UPDATE Motorvogn SET personnr=?, navn=?,adresse=?,kjennetegn=?,merke=?,type=? where id=?";
         db.update(sql,m.getPersonnr(),m.getNavn(),m.getAdresse(),m.getKjennetegn(),m.getMerke(),m.getType(),m.getId());
     }

     public void slettEnMotorvogn(int personnr) {
        String sql = "DELETE FROM Motorvogn WHERE personnr=?";
        db.update(sql,personnr);
    }

    public void slettAlleMotorvogner () {
        String sql = "DELETE FROM Motorvogn";
        db.update(sql);
    }

    public List<Bil> hentAlleBiler(){
        String sql = "SELECT * FROM Bil";
        return db.query(sql,new BeanPropertyRowMapper(Bil.class));
    }
}
