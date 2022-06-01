package repository;

import model.Carte;
import model.Status;

import java.util.List;

public interface RepositoryCarte {
    List<Carte> findCarte(String titlu, String autor);
    Carte findById(String titlu);
    List<Carte> findCarti();
    void updateStatus(String status, Carte carte);
}
