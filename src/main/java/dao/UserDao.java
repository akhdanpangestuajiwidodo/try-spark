package dao;

import java.util.List;
import model.User;
import org.sql2o.Sql2o;

public interface UserDao {
    String insertUser(String userId, String username, int saldo);
    List<User> getAllUser();
}
