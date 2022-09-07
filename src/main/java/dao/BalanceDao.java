package dao;

import java.util.List;
import model.Balance;
import model.BankType;

public interface BalanceDao {
    List<Balance> getBalance(String userId);
    String topUpBalance(int amount, BankType type, String userId);
    String inputBalance(String userid, BankType type);
}
