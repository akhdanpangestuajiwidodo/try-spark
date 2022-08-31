package dao;

import java.util.List;
import model.User;

public interface UserDao {
    String insertUser(String userId, String username, int saldo);
    List<User> getAllUser();
    String loginUser(String username);
    User getStatusUser(String username);
}
