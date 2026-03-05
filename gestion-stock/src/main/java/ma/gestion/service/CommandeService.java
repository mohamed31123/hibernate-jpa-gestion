package ma.gestion.service;

import ma.gestion.classes.Commande;
import ma.gestion.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommandeService extends AbstractFacade<Commande> {

    public CommandeService() {
        super(Commande.class);
    }


    public Commande getById(Integer integer) {
        return null;
    }


    public List<Commande> getAll() {
        return new ArrayList<>();
    }

    // Commandes entre deux dates
    public List<Commande> findBetweenDates(Date d1, Date d2) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Commande> list = session.createQuery(
                        "from Commande c where c.dateCommande between :d1 and :d2",
                        Commande.class)
                .setParameter("d1", d1)
                .setParameter("d2", d2)
                .list();

        session.close();
        return list;
    }
}
