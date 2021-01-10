package oslomet.webprog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class KundeController {

    @Autowired
    private KundeRepository rep;

    @PostMapping("/lagreKunde")
    public void lagreKunde(Kunde kunde, HttpServletResponse response) throws IOException {
        if(!rep.lagreKunde(kunde)) {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB - prøv igjen senere");
        }
    }

    @GetMapping("/hentKunder")
    public List<Kunde> hentAlle (HttpServletResponse response) throws IOException {
        List<Kunde> alleKunder = rep.hentAlleKunder();
        if(alleKunder==null) {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB - prøv igjen senere");
        }
        return alleKunder;
    }

    @GetMapping("/hentEnKunde")
    public Kunde hentEnKunde(int id, HttpServletResponse response) throws IOException {
        Kunde kunden = rep.hentEnKunde(id);
        if(kunden == null){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB - prøv igjen senere");
        }
        return kunden;
    }

    @PostMapping("/endreEnKunde")
    public void endreEnKunde(Kunde kunde,HttpServletResponse response) throws IOException{
        if(!rep.endreEnKunde(kunde)){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB - prøv igjen senere");
        }
    }

    @GetMapping("/slettEnKunde")
    public void slettEnKunde(int id,HttpServletResponse response) throws IOException{
        if(!rep.slettEnKunde(id)){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB - prøv igjen senere");
        }
    }

    @GetMapping("/slettAlleKunder")
    public void slettAlle(HttpServletResponse response) throws IOException{
        if(!rep.slettAlleKunder()){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB - prøv igjen senere");
        }
    }
}