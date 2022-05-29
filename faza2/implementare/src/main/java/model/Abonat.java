package model;

public class Abonat extends Identifiable<Integer>{
    private String cnp;
    private String nume;
    private String adresa;
    private String telefon;

    public Abonat(String cnp, String nume, String adresa, String telefon) {
        this.cnp = cnp;
        this.nume = nume;
        this.adresa = adresa;
        this.telefon = telefon;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
