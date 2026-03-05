package ma.gestion.classes;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NamedQuery(
        name = "Produit.findExpensive",
        query = "from Produit p where p.prix > 100"
)
@Entity
@Table(name = "produits")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String reference;
    private BigDecimal prix;
    @OneToMany(mappedBy = "produits", cascade = CascadeType.ALL)
    private List<LigneCommandeProduit> ligneCommandeProduits;

    @ManyToOne
    @JoinColumn(name = "idCategorie")
    private Categorie categorie;


    public Produit() {
    }

    public Produit(String reference, BigDecimal prix) {
        this.reference = reference;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }



    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }


    public List<LigneCommandeProduit> getLigneCommandeProduits() {
        return ligneCommandeProduits;
    }

    public void setLigneCommandeProduits(List<LigneCommandeProduit> ligneCommandeProduits) {
        this.ligneCommandeProduits = ligneCommandeProduits;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", reference='" + reference + '\'' +
                ", prix=" + prix +
                ", ligneCommandeProduits=" + ligneCommandeProduits +
                ", categorie=" + categorie +
                '}';
    }
}
