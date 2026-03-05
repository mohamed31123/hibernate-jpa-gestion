package ma.gestion.classes;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "employeTache")
public class EmployeTache {
    @EmbeddedId
    private EmployeTacheFK id ;
    private LocalDate dateDebutReel ;
    private LocalDate  dateFinReel ;
    @ManyToOne
    @JoinColumn(name = "idTache" , insertable = false, updatable = false)
    private Tache tache ;

    @ManyToOne
    @JoinColumn(name = "idEmployee" ,insertable=false, updatable=false)
    private Employe employee;
    public EmployeTache(){}

    public EmployeTache(LocalDate dateDebutReel, LocalDate dateFinReel , Employe employee , Tache tache ) {
        this.dateDebutReel = dateDebutReel;
        this.dateFinReel = dateFinReel;
        this.tache = tache;
        this.employee = employee;
        id = new EmployeTacheFK(tache.getId(),employee.getId());

    }

    public EmployeTacheFK getId() {
        return id;
    }

    public void setId(EmployeTacheFK id) {
        this.id = id;
    }

    public LocalDate getDateDebutReel() {
        return dateDebutReel;
    }

    public void setDateDebutReel(LocalDate dateDebutReel) {
        this.dateDebutReel = dateDebutReel;
    }

    public LocalDate getDateFinReel() {
        return dateFinReel;
    }

    public void setDateFinReel(LocalDate dateFinReel) {
        this.dateFinReel = dateFinReel;
    }

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    public Employe getEmployee() {
        return employee;
    }

    public void setEmployee(Employe employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "EmployeTache{" +
                "id=" + id +
                ", dateDebutReel=" + dateDebutReel +
                ", dateFinReel=" + dateFinReel +
                '}';
    }
}
