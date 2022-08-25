package dao;

import java.util.List;
import model.User;
import org.sql2o.Sql2o;

public class UserDaoImplementation implements UserDao {
    @Override
    public String insertUser(Sql2o sql2o, String userId, String username, int saldo) {
        try (org.sql2o.Connection connection = sql2o.open()) {
            connection.createQuery("insert into users VALUES (:userId, :username, :saldo)")
                .addParameter("userId", userId)
                .addParameter("username", username)
                .addParameter("saldo", saldo)
                .executeUpdate();
            return userId;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<User> getAllUser(Sql2o sql2o) {
        try (org.sql2o.Connection conn = sql2o.open()) {
            return conn.createQuery("select * from users").executeAndFetch(User.class);
        }
    }
}
