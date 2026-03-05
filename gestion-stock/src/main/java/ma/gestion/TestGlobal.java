package ma.gestion;

import ma.gestion.classes.*;
import ma.gestion.service.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class TestGlobal {

    public static void main(String[] args) {

        CategorieService catService = new CategorieService();
        ProduitService ps = new ProduitService();
        CommandeService cs = new CommandeService();
        LigneCommandeService ls = new LigneCommandeService();

        // ==========================
        //  CREATION DES CATEGORIES
        // ==========================
        Categorie cat1 = new Categorie("CAT1", "Informatique");
        Categorie cat2 = new Categorie("CAT2", "Electroménager");

        catService.create(cat1);
        catService.create(cat2);

        // ==========================
        //  CREATION DES PRODUITS
        // ==========================
        Produit p1 = new Produit("EGHE0001", new BigDecimal("999"));
        p1.setCategorie(cat1);

        Produit p2 = new Produit("EGHE0002", new BigDecimal("799"));
        p2.setCategorie(cat1);

        Produit p3 = new Produit("EGHE0003", new BigDecimal("699"));
        p3.setCategorie(cat2);

        Produit p4 = new Produit("EGHE0004", new BigDecimal("599"));
        p4.setCategorie(cat2);

        ps.create(p1);
        ps.create(p2);
        ps.create(p3);
        ps.create(p4);

        // ==========================
        // MODIFICATION PRODUIT
        // ==========================
        p4.setReference("ERTY0009");
        ps.update(p4);

        // ==========================
        // SUPPRESSION PRODUIT
        // ==========================
        ps.delete(p3);

        // ==========================
        // CREATION DES COMMANDES
        // ==========================
        Date d1 = Date.from(LocalDate.of(2026, 3, 7)
                .atStartOfDay(ZoneId.systemDefault()).toInstant());

        Date d2 = Date.from(LocalDate.of(2026, 3, 8)
                .atStartOfDay(ZoneId.systemDefault()).toInstant());

        Date d3 = Date.from(LocalDate.of(2026, 3, 9)
                .atStartOfDay(ZoneId.systemDefault()).toInstant());

        Commande c1 = new Commande(d1);
        Commande c2 = new Commande(d1);
        Commande c3 = new Commande(d2);
        Commande c4 = new Commande(d3);

        cs.create(c1);
        cs.create(c2);
        cs.create(c3);
        cs.create(c4);

        // ==========================
        // 6️⃣ LIGNES DE COMMANDE
        // ==========================
        LigneCommandeProduit l1 = new LigneCommandeProduit(2, p1, c2);
        LigneCommandeProduit l2 = new LigneCommandeProduit(1, p2, c2);
        LigneCommandeProduit l3 = new LigneCommandeProduit(3, p4, c2);
        LigneCommandeProduit l4 = new LigneCommandeProduit(5, p2, c4);

        ls.create(l1);
        ls.create(l2);
        ls.create(l3);
        ls.create(l4);

        // ==========================
        // 7️⃣ TEST DES METHODES
        // ==========================

        System.out.println("\n--- Produits chers (>100 DH) ---");
        ps.findExpensiveProducts().forEach(p ->
                System.out.println(p.getReference() + " | " + p.getPrix())
        );

        System.out.println("\n--- Produits par catégorie CAT1 ---");
        ps.findByCategorie(cat1.getId()).forEach(p ->
                System.out.println(p.getReference())
        );

        System.out.println("\n--- Commandes entre 07 et 09 Mars ---");
        cs.findBetweenDates(d1, d3).forEach(c ->
                System.out.println("Commande ID : " + c.getId())
        );

        // ==========================
        // 8️⃣ AFFICHAGE DETAIL COMMANDE
        // ==========================

        Commande c = cs.getById(c2.getId());

        System.out.println("\nCommande : " + c.getId() +
                " | Date : " + c.getDateCommande());

        System.out.println("Liste des produits :");

        List<LigneCommandeProduit> lignes =
                ls.findProduitsByCommande(c.getId());

        for (LigneCommandeProduit l : lignes) {
            System.out.println(
                    l.getProduits().getReference() + " | " +
                            l.getProduits().getPrix() + " DH | Quantité : " +
                            l.getQuantite()
            );
        }

        System.out.println("\n====== FIN TEST ======");
    }
}