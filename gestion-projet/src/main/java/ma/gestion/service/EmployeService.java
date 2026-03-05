package ma.gestion.service;

import ma.gestion.classes.Employe;
import ma.gestion.classes.EmployeTache;
import ma.gestion.classes.Projet;
import ma.gestion.classes.Tache;
import ma.gestion.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.List;

public class EmployeService extends AbstractFacade<Employe> {

    public EmployeService() {
        super(Employe.class);
    }


    public Employe getById(int id) {
        return null;
    }

    public List<Employe> getAll() {
        return findAll();
    }

    // Retourne la liste des tâches réalisées par un employé
    public List<Tache> getTachesByEmployeId(int employeId) {
        Session session = null;
        List<Tache> listTaches = new ArrayList<>();
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            String hql = "SELECT et.tache FROM EmployeTache et WHERE et.employee.id = :employeId";
            Query<Tache> query = session.createQuery(hql, Tache.class);
            query.setParameter("employeId", employeId);
            listTaches = query.getResultList();

            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return listTaches;
    }

    // Retourne la liste des projets gérés par un employé (en tant que chef de projet)
    public List<Projet> getProjetsGererParEmploye(int employeId) {
        Session session = null;
        List<Projet> listeProjet = new ArrayList<>();
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            String hql = "FROM Projet p WHERE p.chefProjet.id = :id";
            listeProjet = session.createQuery(hql, Projet.class)
                    .setParameter("id", employeId)
                    .getResultList();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return listeProjet;
    }
}