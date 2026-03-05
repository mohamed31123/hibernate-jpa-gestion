package ma.gestion;

import ma.gestion.classes.*;
import ma.gestion.service.FemmeService;
import ma.gestion.service.HommeService;
import ma.gestion.service.MarriageService;

import java.text.SimpleDateFormat;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {

        FemmeService femmeService = new FemmeService();
        HommeService hommeService = new HommeService();
        MarriageService marriageService = new MarriageService();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // ================= CREATION HOMMES =================
        Homme h1 = new Homme("CHENAAOUII", "ANAS", 64L, "Marrakech", "0600000000", sdf.parse("01/01/1960"));
        Homme h2 = new Homme("ELKAAMOUNI", "SAID", 39L, "Marrakech", "0600000001", sdf.parse("02/02/1985"));

        hommeService.create(h1);
        hommeService.create(h2);


        Femme f1 = new Femme("SALIMA", "RAMI", 50L, "Casablanca", "0611111111", sdf.parse("01/01/1970"));
        Femme f2 = new Femme("AMAL", "ALI", 48L, "Rabat", "0622222222", sdf.parse("12/05/1972"));
        Femme f3 = new Femme("WAFA", "ALAOUI", 47L, "Tanger", "0633333333", sdf.parse("03/03/1973"));
        Femme f4 = new Femme("KARIMA", "ALAMI", 46L, "Fès", "0644444444", sdf.parse("20/08/1974"));

        femmeService.create(f1);
        femmeService.create(f2);
        femmeService.create(f3);
        femmeService.create(f4);

        Marriage m1 = new Marriage(sdf.parse("03/09/1990"), null, 4, h1, f1);
        Marriage m2 = new Marriage(sdf.parse("03/09/1995"), null, 2, h1, f2);
        Marriage m3 = new Marriage(sdf.parse("04/11/2000"), null, 3, h1, f3);
        Marriage m4 = new Marriage(sdf.parse("03/09/1989"), sdf.parse("03/09/1990"), 0, h1, f4);

        marriageService.create(m1);
        marriageService.create(m2);
        marriageService.create(m3);
        marriageService.create(m4);



        System.out.println("===== Liste des femmes =====");
        List<Femme> femmes = femmeService.findAll();
        for (Femme f : femmes) {
            System.out.println(f.getNom() + " " + f.getPrenom());
        }

        System.out.println("\n===== Femme la plus âgée =====");
        Femme plusAgee = femmeService.findFemmePlusAgee();
        System.out.println(plusAgee.getNom());

        System.out.println("\n===== Femmes mariées au moins 2 fois =====");
        femmeService.femmesMarieesDeuxFois()
                .forEach(f -> System.out.println(f.getNom()));

        System.out.println("\n===== Nombre enfants SALIMA entre 1980 et 2000 =====");
        Long nbr = femmeService.countEnfantsBetweenDates(
                f1.getId(),
                sdf.parse("01/01/1980"),
                sdf.parse("01/01/2000")
        );
        System.out.println(nbr);

        System.out.println("\n===== Mariages de " + h1.getNom() + " =====");
        hommeService.afficherMariagesDetails(h1.getId());
    }
}