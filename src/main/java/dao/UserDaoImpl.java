package dao;

import java.util.List;
import javax.inject.Inject;
import model.UserResponse;
import model.User;
import org.sql2o.Sql2o;

public class UserDaoImpl implements UserDao {

    private final Sql2o sql2o;

    @Inject
    public UserDaoImpl(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public String insertUser(String userId, String username) {
        try (org.sql2o.Connection connection = sql2o.open()) {
            connection.createQuery("insert into users VALUES (:userId, :username)")
                .addParameter("userId", userId)
                .addParameter("username", username)
                .executeUpdate();
            return userId;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<UserResponse> getAllUser() {
        try (org.sql2o.Connection conn = sql2o.open()) {
            return conn.createQuery("select userid, username, status, amount from users inner "
                + "join balance on users.userid = balance.\"userId\"").executeAndFetch(
                UserResponse.class);
        }
    }

    @Override
    public String loginUser(String username) {
        try(org.sql2o.Connection connection = sql2o.open()){
           connection.createQuery("update users set status = true where username = :username")
                .addParameter("username", username)
                .executeUpdate();
           return username;
        }
    }

    @Override
    public User getStatusUser(String username) {
        try (org.sql2o.Connection conn = sql2o.open()) {
            return conn.createQuery("select status from users where username = :username").addParameter("username", username).executeAndFetchFirst(User.class);
        }
    }
}
