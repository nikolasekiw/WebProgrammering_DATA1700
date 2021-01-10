package oslomet.webprog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KundeController {

    @Autowired
    KundeRepository rep;

    @PostMapping("/lagreKunde")
    public void lagreKunde(Kunde kunde){
        rep.lagreKunde(kunde);
    }

    @GetMapping("/hentKunder")
    public List<Kunde> hentAlle(){
        return rep.hentAlleKunder();
    }

    @GetMapping("/hentEnKunde")
    public Kunde hentEnKunde(int id){
        return rep.hentEnKunde(id);
    }

    @PostMapping("/endreEnKunde")
    public void endreEnKunde(Kunde kunde){
        rep.endreEnKunde(kunde);
    }

    @GetMapping("/slettEnKunde")
    public void slettEnKunde(int id){
        rep.slettEnKunde(id);
    }

    @GetMapping("/slettAlleKunder")
    public void slettAlle(){
        rep.slettAlleKunder();
    }
}