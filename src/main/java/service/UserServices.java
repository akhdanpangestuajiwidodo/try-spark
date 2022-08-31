package service;

import dao.UserDao;
import dao.UserDaoImpl;
import java.util.List;
import javax.inject.Inject;
import model.User;
import org.sql2o.Sql2o;

public class UserServices {

    private final UserDao userDao;

    @Inject
    public UserServices(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    public String insertUser(String userId, String username, int saldo) {
        return userDao.insertUser(userId, username, saldo);
    }
}
