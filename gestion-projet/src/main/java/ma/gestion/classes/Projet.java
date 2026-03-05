package ma.gestion.classes;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity

@Table(name = "projet")
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    @ManyToOne
    private Employe chefProjet ;

    @OneToMany(mappedBy = "projet")
    private List<Tache> tache;

    public Projet() {
    }

    public Projet(LocalDate dateDebut, LocalDate dateFin, String nom) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }







    @Override
    public String toString() {
        return "Projet{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                '}';
    }
}
