package service;

import dao.UserDaoImplementation;
import java.util.List;
import model.User;
import org.sql2o.Sql2o;

public class UserServices {

    private final UserDaoImplementation userDao = new UserDaoImplementation();
    private final Sql2o sql2o;

    public UserServices(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public List<User> getAllUser() {
        return userDao.getAllUser(sql2o);
    }

    public String insertUser(String userId, String username, int saldo) {
        return userDao.insertUser(sql2o, userId, username, saldo);
    }
}
