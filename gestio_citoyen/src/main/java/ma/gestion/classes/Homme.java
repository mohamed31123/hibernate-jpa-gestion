package ma.gestion.classes;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "hommes")
public class Homme extends Personne{

    @OneToMany(mappedBy = "homme")
    private List<Marriage> marriage ;

    public Homme(){

    }


    public Homme(String nom, String prenom, Long age, String adresse, String telephone, Date dateNaissance) {
        super(nom, prenom, age, adresse, telephone, dateNaissance);
    }

    public List<Marriage> getMarriages() {
        return marriage;
    }

    public void setMarriages(List<Marriage> marriages) {
        this.marriage = marriages;
    }

    @Override
    public String toString() {
        return "Homme{" +
                "dateNaissance=" + dateNaissance +
                ", telephone='" + telephone + '\'' +
                ", adresse='" + adresse + '\'' +
                ", age=" + age +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", id=" + id +
                ", marriage=" + marriage +
                '}';
    }
}
