package app.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    T get(int key);

    List<T> getAll();

    boolean exist(int key);

    void save(T t);

    void update(T t);

    void delete(T t);
}
