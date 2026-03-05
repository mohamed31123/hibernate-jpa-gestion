package ma.gestion.classes;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "marriage")
public class Marriage {

    @EmbeddedId
    private MarriagePK id;
    private Date dateDebut;
    private Date dateFin;
    private int nbrEnfants ;

    @ManyToOne
    @JoinColumn(name = "idHomme" , nullable = false , insertable = false, updatable = false)
    private Homme homme;

    @ManyToOne
    @JoinColumn(name = "idFemme" , nullable = false , insertable = false, updatable = false)
    private Femme femme;

    public Marriage() {}

    public Marriage(Date dateDebut, Date dateFin, int nbrEnfants, Homme homme, Femme femme) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbrEnfants = nbrEnfants;
        this.homme = homme;
        this.femme = femme;
        id = new MarriagePK(homme.getId(),femme.getId());
    }

    public MarriagePK getId() {
        return id;
    }

    public void setId(MarriagePK id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getNbrEnfants() {
        return nbrEnfants;
    }

    public void setNbrEnfants(int nbrEnfants) {
        this.nbrEnfants = nbrEnfants;
    }

    public Homme getHomme() {
        return homme;
    }

    public void setHomme(Homme homme) {
        this.homme = homme;
    }

    public Femme getFemme() {
        return femme;
    }

    public void setFemme(Femme femme) {
        this.femme = femme;
    }

    @Override
    public String toString() {
        return "Marriage{" +
                "id=" + id +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", nbrEnfants=" + nbrEnfants +
                ", homme=" + homme +
                ", femme=" + femme +
                '}';
    }
}
