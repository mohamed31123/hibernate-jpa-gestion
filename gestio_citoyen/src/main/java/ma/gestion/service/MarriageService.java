package ma.gestion.service;

import ma.gestion.classes.Marriage;

import java.util.List;

public class MarriageService extends AbstractFacade<Marriage> {
    public MarriageService() {
        super(Marriage.class);
    }

    @Override
    public Marriage find(int id) {
        return  findById(id);
    }

    @Override
    public List<Marriage> findAll() {
        return super.findAll();
    }
}
