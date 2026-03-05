package ma.gestion.classes;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "commandes")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date dateCommande;
    @OneToMany(mappedBy = "commandes")

    private List<LigneCommandeProduit> ligneCommandeProduits;

    public Commande() {
    }

    public Commande(Date dateCommande ) {
        this.dateCommande = dateCommande;

    }


    public List<LigneCommandeProduit> getLigneCommandeProduits() {
        return ligneCommandeProduits;
    }

    public void setLigneCommandeProduits(List<LigneCommandeProduit> ligneCommandeProduits) {
        this.ligneCommandeProduits = ligneCommandeProduits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "dateCommande=" + dateCommande +
                ", id=" + id +
                '}';
    }
}
