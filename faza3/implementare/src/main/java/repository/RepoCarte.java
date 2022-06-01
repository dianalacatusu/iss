package repository;

import model.Abonat;
import model.Carte;
import model.Status;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class RepoCarte implements RepositoryCarte{

    private SessionFactory sessionFactory;

    public RepoCarte(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }


    public Carte findById(String titlu) {
        Carte carte = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                carte = session.createQuery("from Carte where titlu='"+titlu+"'", Carte.class).setMaxResults(1).uniqueResult();
                transaction.commit();
            }catch (RuntimeException e){
                System.err.println("Eroare la cauta carte" + e);
                if(transaction!=null)
                    transaction.rollback();
            }
        }
        return carte;
    }

    @Override
    public List<Carte> findCarte(String titlu, String autor) {
        List<Carte> carti = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                carti = session.createQuery("from Carte where titlu='"+titlu+"'", Carte.class).list();
                transaction.commit();
            }catch (RuntimeException e){
                System.err.println("Eroare la cauta carte" + e);
                if(transaction!=null)
                    transaction.rollback();
            }
        }
        return carti;
    }

    @Override
    public List<Carte> findCarti() {
        List<Carte> carti = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                carti = session.createQuery("from Carte", Carte.class).list();
                transaction.commit();
            } catch (RuntimeException e){
                System.err.println("Eroare la find all " + e);
                if (transaction != null)
                    transaction.rollback();
            }
        }
        return carti;
    }

    @Override
    public void updateStatus(String status, Carte carte) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Carte carte1 = findById(carte.getTitlu());
                carte1.setStatus(status);
                session.update(carte.getId().toString(), carte1);
                transaction.commit();
            }catch (RuntimeException e){
                System.err.println("Eroare la actualizare carte" + e);
                if(transaction!=null)
                    transaction.rollback();
            }
        }
    }
}

