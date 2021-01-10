package oslomet.webprog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MotorvognController {

    @Autowired
    private MotorvognRepository rep;

    @GetMapping("/hentBiler")
    public List<Bil> hentBiler() {
        return rep.hentAlleBiler();
    }

    @PostMapping("/lagre")
    public void lagreKunde(Motorvogn motorvogn){
        rep.lagreMotorvogn(motorvogn);
    }

    @GetMapping("/hentAlle")
    public List<Motorvogn> hentAlleMotorvogner(){
        return rep.hentAlleMotorvogner();
    }

    @GetMapping("/henteEnMotorvogn")
    public Motorvogn henteEnMotorvogn(int id){
        return rep.henteEnMotorvogn(id);
    }

    @PostMapping("/endre")
    public void endre(Motorvogn motorvogn){
        rep.endreMotorvogn(motorvogn);
    }

    @GetMapping("/slettEnMotorvogn")
    public void slettEnMotorvogn(int personnr){
        rep.slettEnMotorvogn(personnr);
    }

    @GetMapping("/slettAlle")
    public void slettAlleMotorvogner(){
        rep.slettAlleMotorvogner();
    }
}

