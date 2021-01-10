package oblig3utvidelse.demo;

public class Billett {
    public int id;
    public String fornavn;
    public String etternavn;
    public String telefonnr;
    public String epost;
    public String film;
    public int antall;

    public Billett(int id, String fornavn, String etternavn, String telefonnr, String epost, String film, int antall) {
        this.id = id;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.telefonnr = telefonnr;
        this.epost = epost;
        this.film = film;
        this.antall = antall;
    }

    public Billett() {}
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFornavn() {
        return fornavn;
    }
    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }
    public String getEtternavn() {
        return etternavn;
    }
    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }
    public String getTelefonnr() {
        return telefonnr;
    }
    public void setTelefonnr(String telefonnr) {
        this.telefonnr = telefonnr;
    }
    public String getEpost() {
        return epost;
    }
    public void setEpost(String epost) {
        this.epost = epost;
    }
    public String getFilm() {
        return film;
    }
    public void setFilm(String film) {
        this.film = film;
    }
    public int getAntall() {
        return antall;
    }
    public void setAntall(int antall) {
        this.antall = antall;
    }
}


