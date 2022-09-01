package dao;

import java.util.List;
import javax.inject.Inject;
import model.Balance;
import model.User;
import org.sql2o.Sql2o;

public class BalanceDaoImpl implements BalanceDao{
    private final Sql2o sql2o;

    @Inject
    public BalanceDaoImpl(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public int getBalance(String userId) {
        try (org.sql2o.Connection conn = sql2o.open()) {
            List<User> saldoUser =
                conn.createQuery("select userid, amount from users inner join balance on users"
                        + ".userid = balance.\"userId\" where users.userid = "
                        + ":userid").addParameter("userid", userId)
                    .executeAndFetch(User.class);

            return saldoUser.get(0).getAmount();
        }
    }

    @Override
    public int updateBalance(String userId) {
        return 0;
    }
}
