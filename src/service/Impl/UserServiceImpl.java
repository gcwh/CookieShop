package service.Impl;

import dao.OrderDao;
import dao.UserDao;
import model.Page;
import model.User;
import service.OrderService;
import service.UserService;
import utils.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao userDao=new UserDao();
    OrderDao oDao = new OrderDao();

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
    public boolean addUser(User user) {
        try {
            if(userDao.isUsernameExist(user.getUsername())) {
                return false;
            }
            if(userDao.isEmailExist(user.getEmail())) {
                return false;
            }
            userDao.addUser(user);
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Page getUserPage(int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int pageSize = 7;
        int totalCount = 0;
        try {
            totalCount = userDao.selectUserCount();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        p.SetPageSizeAndTotalCount(pageSize, totalCount);
        List list=null;
        try {
            list = userDao.selectUserList( pageNumber, pageSize);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        p.setList(list);
        return p;
    }

    @Override
    public boolean delete(int id) {
        try {
            userDao.delete(id);
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
}
