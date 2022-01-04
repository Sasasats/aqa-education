package dao;

import java.util.List;

public interface IDao<T> {

    List<T> getAll();

    List<T> get(String request);

    void insert(T t);

    void update(T t);

    void delete(T t);
}
