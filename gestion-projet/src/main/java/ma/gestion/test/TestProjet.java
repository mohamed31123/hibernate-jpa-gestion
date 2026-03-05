package ma.gestion.test;

import ma.gestion.classes.Projet;
import ma.gestion.service.ProjetService;

import java.time.LocalDate;
import java.util.Date;

public class TestProjet {
    public static void main( String[] args )
    {
        ProjetService ps = new  ProjetService();
        Projet projet = new Projet( LocalDate.of(2025,5,12),
                 LocalDate.of(2026, 4, 28),
                "Gestion de magasinn ");
        ps.create(projet);
        Projet p = new Projet();
        System.out.println("Tache planifier");
        ps.getTachesPlanifiees(projet.getId());
        System.out.println("Tache  Projet avec date reel");
        ps.getTachesRealisees(1);

    }
}
