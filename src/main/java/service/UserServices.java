package service;

import dao.UserDao;
import java.util.List;
import javax.inject.Inject;
import model.UserResponse;
import model.User;

public class UserServices {

    private final UserDao userDao;

    @Inject
    public UserServices(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<UserResponse> getAllUser() {
        return userDao.getAllUser();
    }

    public String insertUser(String userId, String username) {
        return userDao.insertUser(userId, username);
    }

    public String loginUser(String username){
        return userDao.loginUser(username);
    }

    public User getStatusUser(String username){
        return userDao.getStatusUser(username);
    }
}
