package dao;

import java.util.List;
import model.Balance;

public interface BalanceDao {
    List<Balance> getBalance(String userId);
    String topUpBalance(int amount, String type, String userId);
    String inputBalance(String userid, String type);
}
