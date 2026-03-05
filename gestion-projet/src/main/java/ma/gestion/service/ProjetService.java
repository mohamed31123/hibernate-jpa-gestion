package ma.gestion.service;

import ma.gestion.classes.Employe;
import ma.gestion.classes.EmployeTache;
import ma.gestion.classes.Projet;
import ma.gestion.classes.Tache;
import ma.gestion.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ProjetService extends AbstractFacade<Projet> {

    public ProjetService() {
        super(Projet.class);
    }

    @Override
    public Projet getById(int id) {
        return getById(id);
    }

    @Override
    public List<Projet> getAll() {
        return findAll();
    }

    public List<EmployeTache> getTachesPlanifiees(int idProjet) {
        Session session = null;
        List<EmployeTache> empTaches = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "FROM employeTache et WHERE et.projet.id = :idProjet ORDER BY t.dateDebut";
            empTaches = session.createQuery(hql, EmployeTache.class)
                    .setParameter("idProjet", idProjet)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return empTaches;
    }

    public List<Tache> getTachesRealisees(int idProjet) {
        Session session = null;
        List<Tache> taches = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "SELECT DISTINCT t FROM Tache t JOIN t.employeesTache et WHERE t.projet.id = :idProjet";
            taches = session.createQuery(hql, Tache.class)
                    .setParameter("idProjet", idProjet)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return taches;
    }
    public void afficherProjetAvecTaches(int idProjet) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Projet projet = session.get(Projet.class, idProjet);
            if (projet == null) {
                System.out.println("Projet introuvable !");
                return;
            }

            SimpleDateFormat sdfShort = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdfLong = new SimpleDateFormat("dd MMMM yyyy");

            System.out.println("\nProjet : " + projet.getId()
                    + "      Nom : " + projet.getNom()
                    + "     Date début : " + sdfLong.format(projet.getDateDebut()));

            System.out.println("Liste des tâches:");
            System.out.printf("%-5s %-15s %-20s %-20s%n",
                    "Num", "Nom", "Date Début Réelle", "Date Fin Réelle");


            List<EmployeTache> etList = session.createQuery(
                            "from EmployeTache et where et.tache.projet.id = :idProjet order by et.tache.id",
                            EmployeTache.class)
                    .setParameter("idProjet", idProjet)
                    .getResultList();

            for (EmployeTache et : etList) {
                Tache t = et.getTache();
                System.out.printf("%-5d %-15s %-20s %-20s%n",
                        t.getId(),
                        t.getNom(),
                        sdfShort.format(et.getDateDebutReel()),
                        sdfShort.format(et.getDateFinReel())
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}