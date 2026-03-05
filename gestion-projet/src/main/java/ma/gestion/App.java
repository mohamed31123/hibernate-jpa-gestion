package ma.gestion;

import ma.gestion.classes.*;
import ma.gestion.service.*;
import ma.gestion.util.HibernateUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class App {

    public static void main(String[] args) {

        HibernateUtil.getSessionFactory().openSession();

        EmployeService employeService = new EmployeService();
        TacheService tacheService = new TacheService();
        ProjetService projetService = new ProjetService();
        EmployeTacheService employeTacheService = new EmployeTacheService();


        Employe e1 = new Employe("Mohamed", "Eddinari", "0612345678");
        Employe e2 = new Employe("Ilyass", "Oubaba", "0623456789");
        employeService.create(e1);
        employeService.create(e2);


        Projet p1 = new Projet(
                LocalDate.of(2026, 1, 14),
                LocalDate.of(2026, 6, 30),
                "Gestion de stock"
        );
        projetService.create(p1);


        Tache t1 = new Tache("Analyse",
                LocalDate.of(2026, 2, 10),
                LocalDate.of(2026, 2, 20),
                4000.0);

        Tache t2 = new Tache("Conception",
                LocalDate.of(2013, 3, 10),
                LocalDate.of(2013, 3, 15),
                6000.0);

        Tache t3 = new Tache("Développement",
                LocalDate.of(2026, 4, 10),
                LocalDate.of(2026, 4, 25),
                15000.0);

        t1.setProjet(p1);
        t2.setProjet(p1);
        t3.setProjet(p1);

        tacheService.create(t1);
        tacheService.create(t2);
        tacheService.create(t3);


        EmployeTache et1 = new EmployeTache(
                LocalDate.of(2026, 2, 10),
                LocalDate.of(2026, 3, 20),
                e1, t1);

        EmployeTache et2 = new EmployeTache(
                LocalDate.of(2026, 3, 10),
                LocalDate.of(2026, 3, 15),
                e2, t2);

        EmployeTache et3 = new EmployeTache(
                LocalDate.of(2026, 4, 10),
                LocalDate.of(2026, 4, 25),
                e1, t3);

        employeTacheService.create(et1);
        employeTacheService.create(et2);
        employeTacheService.create(et3);


        DateTimeFormatter formatLong = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        DateTimeFormatter formatShort = DateTimeFormatter.ofPattern("dd/MM/yyyy");


        System.out.println("\nProjet : " + p1.getId()
                + "      Nom : " + p1.getNom()
                + "     Date début : " + p1.getDateDebut().format(formatLong));

        System.out.println("Liste des tâches:");
        System.out.printf("%-5s %-15s %-20s %-20s%n",
                "Num", "Nom", "Date Début Réelle", "Date Fin Réelle");

        List<EmployeTache> liste = employeTacheService.getAll();

        for (EmployeTache et : liste) {
            if (et.getTache().getProjet().getId() == p1.getId()) {

                System.out.printf("%-5d %-15s %-20s %-20s%n",
                        et.getTache().getId(),
                        et.getTache().getNom(),
                        et.getDateDebutReel().format(formatShort),
                        et.getDateFinReel().format(formatShort)
                );
            }
        }
    }
}