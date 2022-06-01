package service;

import model.Abonat;
import model.Angajat;
import model.Carte;
import model.Status;
import repository.RepoAngajat;
import repository.RepositoryAngajat;
import repository.RepositoryCarte;

import java.util.List;

public class Service {
    private RepositoryAngajat repoAngajat;
    private RepositoryCarte repoCarte;

    public Service(RepositoryAngajat repoAngajat, RepositoryCarte repoCarte) {
        this.repoAngajat = repoAngajat;
        this.repoCarte = repoCarte;
    }

    public Angajat loginAngajat(String username, String password){
        return repoAngajat.login(username, password);
    }

    public Abonat loginAbonat(String cnp){
        return repoAngajat.cautaAbonat(cnp);
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

    public List<Carte> findCarte(String titlu, String autor){
        return repoCarte.findCarte(titlu, autor);
    }

    public Carte findById(String titlu){
        return repoCarte.findById(titlu);
    }
    public List<Carte> findCarti(){
        return repoCarte.findCarti();
    }
    public void updateStatus(String status, Carte carte){
        repoCarte.updateStatus(status, carte);
    }
}
