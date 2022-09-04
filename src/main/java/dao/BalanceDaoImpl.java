package dao;

import java.util.List;
import javax.inject.Inject;
import model.Balance;
import org.sql2o.Sql2o;

public class BalanceDaoImpl implements BalanceDao{
    private final Sql2o sql2o;

    @Inject
    public BalanceDaoImpl(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public List<Balance> getBalance(String userId) {
        try (org.sql2o.Connection conn = sql2o.open()) {

            return conn.createQuery("select * from balance where \"userId\" = :userid").addParameter("userid", userId)
                .executeAndFetch(Balance.class);
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public int updateBalance(String userId) {
        return 0;
    }

    @Override
    public String inputBalance(String balanceId, String userId) {
        try (org.sql2o.Connection connection = sql2o.open()) {
            connection.createQuery("insert into balance VALUES (:balanceId, :userId)")
                .addParameter("balanceId", balanceId)
                .addParameter("userId", userId)
                .executeUpdate();
            return userId;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
