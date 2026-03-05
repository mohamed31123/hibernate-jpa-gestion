package ma.gestion.service;

import ma.gestion.classes.Tache;
import ma.gestion.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TacheService extends AbstractFacade<Tache> {

    public TacheService() {
        super(Tache.class);
    }

    @Override
    public Tache getById(int id) {
        return getById(id);
    }


    @Override
    public List<Tache> getAll() {
        return findAll();
    }

    public List<Tache> getTachesPrixSuperieurA1000() {
        Session session = null;
        List<Tache> taches = new ArrayList<>();
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            String hql = "FROM Tache t WHERE t.prix >= 1000";
            Query<Tache> query = session.createQuery(hql, Tache.class);
            taches = query.getResultList();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return taches;
    }

    public List<Tache> getTachesEntreDeuxDates(Date dateDebut, Date dateFin) {
        Session session = null;
        List<Tache> taches = new ArrayList<>();
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            String hql = "FROM Tache t WHERE t.dateDebut BETWEEN :debut AND :fin";
            Query<Tache> query = session.createQuery(hql, Tache.class);
            query.setParameter("debut", dateDebut);
            query.setParameter("fin", dateFin);
            taches = query.getResultList();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return taches;
    }
}