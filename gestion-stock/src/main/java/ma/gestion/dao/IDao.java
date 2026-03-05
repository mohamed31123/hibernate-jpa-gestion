package ma.gestion.dao;

import java.io.Serializable;
import java.util.List;

public interface IDao<T , ID extends Serializable> {
    boolean create(T o);
    boolean update(T o);
    boolean delete(T o);
    T getById(ID id);
    List<T> getAll();

}
