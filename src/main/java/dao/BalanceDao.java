package dao;

import java.util.List;
import model.Balance;

public interface BalanceDao {
    List<Balance> getBalance(String userId);
    int updateBalance(String userId);
    String inputBalance(String userid, String type);
}
