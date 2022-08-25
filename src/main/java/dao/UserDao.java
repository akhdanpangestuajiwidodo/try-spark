package dao;

import java.util.List;
import model.User;
import org.sql2o.Sql2o;

public interface UserDao {
    public String insertUser(Sql2o sql2o, String userId, String username, int saldo);
    public List<User> getAllUser(Sql2o sql2o);
}
