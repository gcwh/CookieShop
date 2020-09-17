package service.Impl;

import dao.UserDao;
import model.User;
import service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    UserDao userDao=new UserDao();

    @Override
    public User selectByUsernamePassword(String username, String password) {
        User user=null;
        try {
            user=userDao.selectByUsernamePassword(username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User selectByEmailPassword(String email, String password) {
        User user=null;
        try {
            user=userDao.selectByEmailPassword(email,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void updateUserAddress(User user) {
        try {
            userDao.updateUserAddress(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User selectById(int id) {
        User user=null;
        try {
            user=userDao.selectById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void updatePwd(User user) {
        try {
            userDao.updatePwd(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isUsernameExist(String username) {
        boolean isExist=false;
        try {
            isExist=userDao.isUsernameExist(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isExist;
    }

    @Override
    public boolean isEmailExist(String email) {
        boolean isExist=false;
        try {
            isExist=userDao.isEmailExist(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isExist;
    }

    @Override
    public void addUser(User user) {
        try {
            userDao.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
