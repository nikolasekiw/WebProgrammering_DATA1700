package oslomet.webprog;

public class Ordre {
    private String fornavn;
    private String etternavn;
    private String adresse;
    private String varenavn;
    private double pris;

    public Ordre(String fornavn, String etternavn, String adresse, String varenavn, double pris) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.adresse = adresse;
        this.varenavn = varenavn;
        this.pris = pris;
    }

    public Ordre() {
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVarenavn() {
        return varenavn;
    }

    public void setVarenavn(String varenavn) {
        this.varenavn = varenavn;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }
}
