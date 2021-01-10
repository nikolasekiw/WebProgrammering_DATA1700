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
public class MotorvognController {

    @Autowired
    private MotorvognRepository rep;

    @GetMapping("/hentBiler")
    public List<Bil> hentBiler(HttpServletResponse response) throws IOException {
        List<Bil> alleBiler = rep.hentAlleBiler();
        if(alleBiler==null){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB -prøv igjen senere");
        }
        return alleBiler;
    }

    @PostMapping("/lagre")
    public void lagreKunde(Motorvogn motorvogn, HttpServletResponse response) throws IOException {
        if(!rep.lagreMotorvogn(motorvogn)){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB -prøv igjen senere");
        }
    }

    @GetMapping("/hentAlle")
    public List<Motorvogn> hentAlleMotorvogner(HttpServletResponse response) throws IOException {
        List<Motorvogn> alleMotorvogner = rep.hentAlleMotorvogner();
        if(alleMotorvogner==null){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB -prøv igjen senere");
        }
        return alleMotorvogner;
    }

    @GetMapping("/henteEnMotorvogn")
    public Motorvogn henteEnMotorvogn(int id, HttpServletResponse response) throws IOException{
        Motorvogn enMotorvogn = rep.henteEnMotorvogn(id);
        if(enMotorvogn == null){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB -prøv igjen senere");
        }
        return enMotorvogn;
    }

    @PostMapping("/endre")
    public void endre(Motorvogn motorvogn, HttpServletResponse response) throws IOException{
        if(!rep.endreMotorvogn(motorvogn)){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB -prøv igjen senere");
        }
    }

    @GetMapping("/slettEnMotorvogn")
    public void slettEnMotorvogn(int personnr, HttpServletResponse response) throws IOException{
        if(!rep.slettEnMotorvogn(personnr)){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB -prøv igjen senere");
        }
    }

    @GetMapping("/slettAlle")
    public void slettAlleMotorvogner(HttpServletResponse response) throws IOException{
        if(!rep.slettAlleMotorvogner()){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB -prøv igjen senere");
        }
    }
}

