package oblig3utvidelse.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private BillettRepository rep;

    @PostMapping("/lagre")
    public void lagreKunde(Billett enBillett) {
        rep.lagreBillett(enBillett);
    }

    @GetMapping ("/hentAlle")
    public List<Billett> hentAlle() {
        List<Billett> alleBilletter = rep.hentAlle();
        return alleBilletter;
    }

    @GetMapping("hentAlleFilmer")
    public List<Film> hentAlleFilmer(){
        List<Film> alleFilmer = rep.hentAlleFilmer();
        return alleFilmer;
    }

    @PostMapping("/slettAlle")
    public void slettAlle() {
        rep.slettAlleBilletter();
    }


}
