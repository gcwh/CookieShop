package service;

import model.Page;
import model.User;

public interface UserService {

    User selectByUsernamePassword(String username,String password);

    User selectByEmailPassword(String email,String password);

    boolean isUsernameExist(String username);

    boolean isEmailExist(String email);

    boolean addUser(User user);

    void updateUserAddress(User user);

    User selectById(int id);

    void updatePwd(User user);

    boolean delete(int id);

    Page getUserPage(int pageNumber);
}
