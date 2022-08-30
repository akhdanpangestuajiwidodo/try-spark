package service;

import dao.UserDaoImpl;
import java.util.List;
import javax.inject.Inject;
import model.User;
import org.sql2o.Sql2o;

public class UserServices {

    private final UserDaoImpl userDao;

    private final Sql2o sql2o;

    @Inject
    public UserServices(UserDaoImpl userDao, Sql2o sql2o) {
        this.userDao = userDao;
        this.sql2o = sql2o;
    }

    public List<User> getAllUser() {
        return userDao.getAllUser(sql2o);
    }

    public String insertUser(String userId, String username, int saldo) {
        return userDao.insertUser(sql2o, userId, username, saldo);
    }
}
