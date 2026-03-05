package ma.gestion.classes;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MarriagePK implements Serializable {
    private Long idHomme ;
    private Long IdFemme ;

    public MarriagePK() {
    }
    public MarriagePK(Long idHomme , Long IdFemme) {
        this.idHomme = idHomme;
        this.IdFemme = IdFemme;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MarriagePK that = (MarriagePK) o;
        return idHomme == that.idHomme && IdFemme == that.IdFemme;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idHomme, IdFemme);
    }
}


