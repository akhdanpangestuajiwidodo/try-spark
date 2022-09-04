package dao;

import java.util.List;
import java.util.UUID;
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
    public String topUpBalance(int amount, String type, String userId) {
        try(org.sql2o.Connection connection = sql2o.open()){
            connection.createQuery("update balance set amount = (amount + :amount) where \"userId\" = :userid and type = :type")
                .addParameter("amount", amount)
                .addParameter("userid", userId)
                .addParameter("type", type)
                .executeUpdate();
            return userId;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public String inputBalance(String userId, String type) {
        try (org.sql2o.Connection connection = sql2o.open()) {
            UUID balanceId = UUID.randomUUID();
            connection.createQuery("insert into balance (\"balanceId\",\"type\", \"userId\") VALUES (:balanceId, :type, :userId)")
                .addParameter("type", type)
                .addParameter("balanceId", balanceId)
                .addParameter("userId", userId)
                .executeUpdate();
            return "the balance id is" + balanceId;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
