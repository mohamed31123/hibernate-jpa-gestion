package ma.gestion.service;

import ma.gestion.classes.EmployeTache;
import ma.gestion.classes.Projet;
import ma.gestion.classes.Tache;
import ma.gestion.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeTacheService extends AbstractFacade<EmployeTache> {
    public EmployeTacheService() {
        super(EmployeTache.class);
    }

    @Override
    public EmployeTache getById(int id) {
        return getById(id);
    }

    @Override
    public List<EmployeTache> getAll() {
        return findAll();
    }




}
