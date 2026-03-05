package ma.gestion.test;

import ma.gestion.classes.Employe;
import ma.gestion.service.EmployeService;
import ma.gestion.util.HibernateUtil;

import java.util.List;

 public class TestEmployee {
    static final EmployeService employeeService = new EmployeService();

    public boolean ajouterEmployee(Employe e) {
        employeeService.create(e);
        return true;
    }

    public boolean supprimerEmployee(Employe e) {
        employeeService.delete(e);
        return true;
    }

    public boolean modifierEmployee(Employe e) {
        employeeService.update(e);
        return true;
    }


    public Employe trouverEmployee(Employe e) {
        return employeeService.getById(e.getId());
    }

    public List<Employe> listAllEmployees() {
        return employeeService.getAll();
    }
    public  static void main(String[] args){






    }
}
