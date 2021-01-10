package oslomet.webprog;

public class Kunde {
    private String fornavn;
    private String etternavn;
    private String eierBil;
    private boolean forerkort; // ikke ø da javascript ikke er spesielt glad i det.

    public Kunde(String fornavn, String etternavn, String eierBil, boolean forerkort) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.eierBil = eierBil;
        this.forerkort = forerkort;
    }

    public Kunde() {
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

    public String getEierBil() {
        return eierBil;
    }

    public void setEierBil(String eierBil) {
        this.eierBil = eierBil;
    }

    public boolean isForerkort() {
        return forerkort;
    }

    public void setForerkort(boolean forerkort) {
        this.forerkort = forerkort;
    }
}
