package oslomet.webprog;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MotorvognController {
    @Autowired private MotorvognRepository rep;
    @Autowired private HttpSession session;
    /**
     * Logger brukes for å gi tilbakemelding til programmereren om det er noe feil i systemet.
     * Loggen kommer som en del av pache/loggen som kommer under når vi kjører applikasjonen i intellij.
     */
    Logger logger = LoggerFactory.getLogger(MotorvognController.class);
    /**
     * Her er det brukt post fordi vi skal sende noe til databasen, vi skal lagre en motorvogn.
     * Her sjekkes også at valideringen av motorvognen er riktig/ok, hvis den ikke er det så skrives ut
     * tilbakemeldingen om at det er noe feil i databasen.
     */
    @PostMapping("/lagre")
    public void lagreEnMotorvogn(Motorvogn m, HttpServletResponse response) throws IOException {
        if(!validerMotorvognOK(m)){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i db - prøv igjen senere");
        }
        else if(!rep.lagreEnMotorvogn(m)){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i db - prøv igjen senere");
        }
    }

    /**
     * Her brukes get fordi vi skal hente noe fra databasen. Ettersom kun en administrator kan hente ting så
     * sjekkes det derfor om personen er logget inn, slik at man kun da kan se alle registrerte motorvogner.
     */
    @GetMapping("/hentAlle")
    public List<Motorvogn> hentAlleMotorvogner(HttpServletResponse response) throws IOException {
        if((boolean)session.getAttribute("loggetInn")){
            List<Motorvogn> alleMotorvogner = rep.hentAlleMotorvogner();
            if(alleMotorvogner == null){
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i db - prøv igjen senere");
            }
            return alleMotorvogner;
        } else {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Kan ikke vise register: Du må være logget inn");
            return null;
        }
    }
    /**
     * Vi må gjøre en sjekk i heltAnlleMotorvogner for om det er en aktiv sesjon/om jeg er logget inn. Måten å
     * gjøre det på er å sjekke sesjonsattributtet loggetInn. Hvis man derimot ikke er logget inn (else), så vil
     * vi at det sksal sendes en feilmelding og ikke returneres noen motorvogner.
     */

    @GetMapping("hentEnMotorvogn")
    public Motorvogn hentEnMotorvogn(int id, HttpServletResponse response) throws IOException {
        Motorvogn enMotorvogn = rep.hentEnMotorvogn(id);
        if(enMotorvogn == null){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i db - prøv igjen senere");
        }
        return enMotorvogn;
        //kunne også ha skrevet: return enMotorvogn.get(0); for å ta høyde for hvis det er flere rader,
        // men av en eller annen grunn fikk jeg opp feil når jeg prøvde å gjøre det her
    }

    @GetMapping("/hentBiler")
    public List<Bil> hentBiler(HttpServletResponse response) throws IOException {
        List<Bil> alleBiler = rep.hentAlleBiler();
        if(alleBiler == null){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i db - prøv igjen senere");
        }
        return alleBiler;
    }
    @PostMapping("/endreEnMotorvogn")
    public void endreEnMotorvogn(Motorvogn motorvogn, HttpServletResponse response) throws IOException {
        if(!validerMotorvognOK(motorvogn)){
            response.sendError(HttpStatus.NOT_ACCEPTABLE.value());
        } else {
            if(!rep.endreEnMotorvogn(motorvogn)){
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i db - prøv igjen senere");
            }
        }
    }

    @GetMapping("/slettEnMotorvogn")
    public void slettEnMotorvogn(String personnr, HttpServletResponse response) throws IOException {
        if(!rep.slettEnMotorvogn(personnr)){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i db - prøv igjen senere");
        }
    }

    @GetMapping("/slettAlle")
    public void slettAlleMotorvogner(HttpServletResponse response) throws IOException {
        if(!rep.slettAlleMotorvogner()){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i db - prøv igjen senere");
        }
    }
    public boolean validerMotorvognOK(Motorvogn motorvogn){
        String regexPersonnr = "[0-9]{11}";
        String regexNavn = "[a-zA-ZæøåÆØÅ. \\-]{2,20}";
        String regexPassord = "(?=.*[a-zA-ZæøåÆØÅ])(?=.*\\d)[a-zA-ZæøåÆØÅ\\d]{8,}";
        String regexAdresse = "[0-9a-zA-ZæøåÆØÅ. \\-]{2,50}";
        String regexKjennetegn = "[0-9a-zA-ZæøåÆØÅ. \\-]{2,20}";
        String regexMerke = "[a-zA-ZæøåÆØÅ.\\-]{2,10}";
        boolean personnrOK = motorvogn.getPersonnr().matches(regexPersonnr);
        boolean navnOK = motorvogn.getNavn().matches(regexNavn);
        boolean adresseOK = motorvogn.getAdresse().matches(regexAdresse);
        boolean kjennetegnOK = motorvogn.getKjennetegn().matches(regexKjennetegn);
        boolean merkeOK = motorvogn.getMerke().matches(regexMerke);
        if(personnrOK && navnOK && adresseOK && kjennetegnOK && merkeOK){
            return true;
        }
        logger.error("Valideringsfeil");
        return false;
    }
    /**
     * Her kunne vi også istedenfor skrevet: session.getAttribute("Innlogget", bruker);
     * Denne sesjonen vil leve inntil nettleseren lukkes. Dersom variabelen ikke benyttes innen 30 min,
     * slettes sesjonen (dvs. objektet og nøkkelen i hash-mapen slettes)
     */
    @GetMapping("/loggInn")
    public boolean loggInn(String brukernavn, String passord){ //gir true hvis det er riktig brukernavn og passord
        if(rep.loggInn(brukernavn, passord)){
            session.setAttribute("loggetInn", true);
            return true;
        } else {
            return false;
        }
    }
    /**
     * Hvis vi heller hadde skrevet session.removeAttribute("loggetInn"),
     * så tar vi vekk objektet og nøkkelen fra sesjonen (hash-mappen)
     */
    @GetMapping("/loggUt")
    public void loggUt(){
        session.setAttribute("loggetInn", false);
    }

    @GetMapping("/krypterAllePassord")
    public boolean krypterAllePassord() {
        return rep.krypterAllePassord();
    }
}