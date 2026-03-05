
package ma.gestion;

import ma.gestion.classes.*;
import ma.gestion.service.CommandeService;
import ma.gestion.service.LigneCommandeService;
import ma.gestion.service.ProduitService;
import ma.gestion.util.HibernateUtil;
import org.h2.command.Command;
import org.h2.command.CommandContainer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class App {
    public static void main(String[] args) {
        HibernateUtil.getSessionFactory().openSession();

    }
}


















