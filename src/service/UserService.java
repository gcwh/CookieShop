package service;

import model.User;

public interface UserService {

    User selectByUsernamePassword(String username,String password);

    User selectByEmailPassword(String email,String password);

    boolean isUsernameExist(String username);

    boolean isEmailExist(String email);

    void addUser(User user);
}
