package service;

import model.Type;

import java.util.List;

public interface TypeService {
    List<Type> GetAllType();

    Type selectTypeNameByID(int typeid);

    Type select(int id);

    void insert(Type type);

    void update(Type type);

    boolean delete(int id);
}
