package ma.gestion.service;

import ma.gestion.classes.Categorie;

import java.util.ArrayList;
import java.util.List;

public class CategorieService extends AbstractFacade<Categorie> {
    public CategorieService() {
        super(Categorie.class);
    }

    @Override
    public Categorie getById(Integer integer) {
        return null;
    }

    @Override
    public List<Categorie> getAll() {
        return new ArrayList<>();
    }
}
