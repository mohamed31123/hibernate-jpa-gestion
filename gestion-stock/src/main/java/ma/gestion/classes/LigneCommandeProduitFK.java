package ma.gestion.classes;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LigneCommandeProduitFK implements Serializable {
    private int idProduit;
    private int idCommande;

    public LigneCommandeProduitFK() {}

    public LigneCommandeProduitFK(int idProduit, int idCommande) {
        this.idProduit = idProduit;
        this.idCommande = idCommande;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LigneCommandeProduitFK that = (LigneCommandeProduitFK) o;
        return idProduit == that.idProduit && idCommande == that.idCommande;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduit, idCommande);
    }


}
