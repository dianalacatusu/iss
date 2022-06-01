package repository;

import model.Abonat;
import model.Angajat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.util.List;

public class RepoAngajat implements RepositoryAngajat{
    private SessionFactory sessionFactory;

    public RepoAngajat(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }


    @Override
    public Angajat login(String username, String password) {
        Angajat angajat = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                angajat = session.createQuery("from Angajat where username='"+username+"'", Angajat.class).setMaxResults(1).uniqueResult();
                transaction.commit();
            }catch (RuntimeException e){
                System.err.println("Eroare la login" + e);
                if(transaction!=null)
                    transaction.rollback();
            }
        }
        return angajat;
    }

    @Override
    public void adaugaAbonat(Abonat abonat) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                session.save(abonat);
                transaction.commit();
            }catch (RuntimeException e){
                System.err.println("Eroare la adauga abonat" + e);
                if(transaction!=null)
                    transaction.rollback();
            }
        }
    }

    @Override
    public Abonat cautaAbonat(String cnp) {
        Abonat abonat = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                abonat = session.createQuery("from Abonat where cnp='"+cnp+"'", Abonat.class).setMaxResults(1).uniqueResult();
                transaction.commit();
            }catch (RuntimeException e){
                System.err.println("Eroare la cauta abonat" + e);
                if(transaction!=null)
                    transaction.rollback();
            }
        }
        return abonat;
    }

    @Override
    public void stergeAbonat(String cnp) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Abonat abonat = cautaAbonat(cnp);
                session.delete(abonat);
                transaction.commit();
            }catch (RuntimeException e){
                System.err.println("Eroare la sterge abonat" + e);
                if(transaction!=null)
                    transaction.rollback();
            }
        }
    }

    @Override
    public void actualizareAbonat(String cnp, Abonat abonat) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Abonat ab = cautaAbonat(cnp);
                ab.setNume(abonat.getNume());
                ab.setAdresa(abonat.getAdresa());
                ab.setTelefon(abonat.getTelefon());
                session.update(cnp, ab);
                transaction.commit();
            }catch (RuntimeException e){
                System.err.println("Eroare la actualizare abonat" + e);
                if(transaction!=null)
                    transaction.rollback();
            }
        }
    }
}