package service.Impl;

import dao.TypeDao;
import model.Type;
import service.TypeService;

import java.sql.SQLException;
import java.util.List;

public class TypeServiceImpl implements TypeService {
    TypeDao typeDao = new TypeDao();

    @Override
    public List<Type> GetAllType() {
        List<Type> list = null;
        try {
            list = typeDao.GetAllType();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public Type selectTypeNameByID(int typeid) {
        Type type = null;
        try {
            type = typeDao.selectTypeNameByID(typeid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return type;
    }

    @Override
    public Type select(int id) {
        Type type = null;
        try {
            type = typeDao.select(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return type;
    }


    @Override
    public void insert(Type type) {
        try {
            typeDao.insert(type);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Type type) {
        try {
            typeDao.update(type);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            typeDao.delete(id);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
