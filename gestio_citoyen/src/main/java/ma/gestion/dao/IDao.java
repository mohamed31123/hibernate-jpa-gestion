package ma.gestion.dao;

import java.io.Serializable;
import java.util.List;

public interface IDao<T, ID  extends Serializable> {
    public boolean create(T entity);
    public boolean update(T entity);
    public boolean delete(T entity);
    public T find(int id);
    public List<T> findAll();
}
