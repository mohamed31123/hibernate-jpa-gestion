package ma.gestion.classes;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ligneCommandesProduits")
public class LigneCommandeProduit {

    @EmbeddedId
    private LigneCommandeProduitFK id ;

    private int quantite;

    @ManyToOne
    @JoinColumn(name = "idProduit",insertable = false, updatable = false )
    private Produit produits;

    @ManyToOne
    @JoinColumn(name = "idCommande" , insertable = false, updatable = false)
    private Commande commandes;


    public LigneCommandeProduit() {}

    public LigneCommandeProduit(int quantite, Produit produits, Commande commandes) {
        this.quantite = quantite;
        this.produits = produits;
        this.commandes = commandes;
        id = new LigneCommandeProduitFK(produits.getId(), commandes.getId());
    }

    public LigneCommandeProduitFK getId() {
        return id;
    }

    public void setId(LigneCommandeProduitFK id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Produit getProduits() {
        return produits;
    }


    public void setProduits(Produit produits) {
        this.produits = produits;
    }

    public Commande getCommandes() {
        return commandes;
    }

    public void setCommandes(Commande commandes) {
        this.commandes = commandes;
    }

    @Override
    public String toString() {
        return "LigneCommandeProduit{" +
                "id=" + id +
                ", quantite=" + quantite +
                '}';
    }
}
