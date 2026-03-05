package ma.gestion.classes;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "femmes")


@NamedNativeQuery(
        name = "Femme.countEnfantsBetweenDates",
        query = "SELECT SUM(m.nbrEnfants) FROM marriage m WHERE m.IdFemme = ?1 AND m.dateDebut BETWEEN ?2 AND ?3"
)
@NamedQuery(
        name = "Femme.findMarriedTwice",
        query = "SELECT f FROM Femme f WHERE SIZE(f.marriage) >= 2"
)

public class Femme extends Personne {


     @OneToMany(mappedBy = "femme")
    private List<Marriage> marriage ;
    public Femme() {
    }


    public Femme(String nom, String prenom, Long age, String adresse, String telephone, Date dateNaissance) {
        super(nom, prenom, age, adresse, telephone, dateNaissance);
        this.marriage = marriage;
    }

    public List<Marriage> getMarriages() {
        return marriage;
    }

    public void setMarriages(List<Marriage> marriages) {
        this.marriage = marriages;
    }

    @Override
    public String toString() {
        return "Femme{" +
                "marriage=" + marriage +
                ", id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", age=" + age +
                ", adresse='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", dateNaissance=" + dateNaissance +
                '}';
    }
}
