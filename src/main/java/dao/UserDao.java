package dao;

import java.util.List;
import model.UserResponse;
import model.User;

public interface UserDao {
    String insertUser(String username);
    List<UserResponse> getAllUser();
    String loginUser(String username);
    User getStatusUser(String username);
}
