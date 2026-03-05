package ma.gestion.service;

import ma.gestion.classes.LigneCommandeProduit;
import ma.gestion.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class LigneCommandeService extends AbstractFacade<LigneCommandeProduit> {

    public LigneCommandeService() {
        super(LigneCommandeProduit.class);
    }

    @Override
    public LigneCommandeProduit getById(Integer integer) {
        return null;
    }

    @Override
    public List<LigneCommandeProduit> getAll() {
        return new ArrayList<>();
    }

    public List<LigneCommandeProduit>
    findProduitsByCommande(int commandeId) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<LigneCommandeProduit> list = session.createQuery(
                        "from LigneCommandeProduit l where l.commandes.id = :id",
                        LigneCommandeProduit.class)
                .setParameter("id", commandeId)
                .list();

        session.close();
        return list;
    }
}