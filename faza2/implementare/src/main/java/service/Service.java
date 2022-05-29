package service;

import model.Abonat;
import model.Angajat;
import repository.RepoAngajat;
import repository.RepositoryAngajat;

public class Service {
    private RepositoryAngajat repoAngajat;

    public Service(RepositoryAngajat repoAngajat) {
        this.repoAngajat = repoAngajat;
    }

    public Angajat loginAngajat(String username, String password){
        return repoAngajat.login(username, password);
    }

    public void adaugaAbonat(Abonat abonat){
        repoAngajat.adaugaAbonat(abonat);
    }

    public Abonat cautaAbonat(String cnp){
        return repoAngajat.cautaAbonat(cnp);
    }

    public void stergeAbonat(String cnp){
        repoAngajat.stergeAbonat(cnp);
    }

    public void actualizareAbonat(String cnp, Abonat abonat){
        repoAngajat.actualizareAbonat(cnp, abonat);
    }
}
