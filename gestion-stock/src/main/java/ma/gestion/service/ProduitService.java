package ma.gestion.service;

import ma.gestion.classes.Produit;
import ma.gestion.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public  class  ProduitService extends AbstractFacade<Produit> {

    public ProduitService() {
        super(Produit.class);
    }


    public Produit getById(Integer integer) {
        return getById(integer);
    }


    public List<Produit> getAll() {
        return getAll();
    }


    // 🔹 Produits par catégorie
    public List<Produit> findByCategorie(int categorieId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Produit> list = session.createQuery(
                        "from Produit p where p.categorie.id = :id", Produit.class)
                .setParameter("id", categorieId)
                .list();
        session.close();
        return list;
    }

    // Produits prix > 100 DH (requête nommée)
    public List<Produit> findExpensiveProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Produit> list = session
                .createNamedQuery("Produit.findExpensive", Produit.class)
                .list();
        session.close();
        return list;
    }
}
