package service;

public interface CRUDService<T> {
    int create(T entity);
    T read(int id);
    void update(T entity);
    void delete(int id);
}
