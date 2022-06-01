package repository;

import model.Abonat;
import model.Angajat;

public interface RepositoryAngajat {
    Angajat login(String username, String password);
    void adaugaAbonat(Abonat abonat);
    Abonat cautaAbonat(String cnp);
    void stergeAbonat(String cnp);
    void actualizareAbonat(String cnp, Abonat abonat);
}
