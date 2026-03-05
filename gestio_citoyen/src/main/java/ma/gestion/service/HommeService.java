package ma.gestion.service;

import ma.gestion.classes.Femme;
import ma.gestion.classes.Homme;
import ma.gestion.classes.Marriage;
import ma.gestion.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HommeService extends AbstractFacade<Homme> {
    public HommeService() {
        super(Homme.class);
    }

    @Override
    public Homme find(int id) {
        return null;
    }

    public void afficherEpousesEntreDates(Long hommeId, LocalDate d1, LocalDate d2) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Marriage> mariages = session.createQuery(
                        "FROM Marriage m WHERE m.homme.id = :id AND m.dateDebut BETWEEN :d1 AND :d2",
                        Marriage.class)
                .setParameter("id", hommeId)
                .setParameter("d1", d1)
                .setParameter("d2", d2)
                .list();

        for (Marriage m : mariages) {
            System.out.println(m.getFemme().getNom());
        }

        session.close();
    }
    public void afficherMariagesDetails(Long hommeId) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Homme homme = session.get(Homme.class, hommeId);

        if (homme == null) {
            System.out.println("Homme introuvable");
            session.close();
            return;
        }

        System.out.println("Nom : " + homme.getNom() + " " + homme.getPrenom());

        for (Marriage m : homme.getMarriages()) {

            if (m.getDateFin() == null) {
                System.out.println("En cours -> Femme : "
                        + m.getFemme().getNom()
                        + " | Date début : "
                        + m.getDateDebut()
                        + " | Enfants : "
                        + m.getNbrEnfants());
            } else {
                System.out.println("Échoué -> Femme : "
                        + m.getFemme().getNom()
                        + " | Date début : "
                        + m.getDateDebut()
                        + " | Date fin : "
                        + m.getDateFin()
                        + " | Enfants : "
                        + m.getNbrEnfants());
            }
        }

        session.close();
    }
}
