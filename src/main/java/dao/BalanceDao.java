package dao;

public interface BalanceDao {
    int getBalance(String userId);
    int updateBalance(String userId);
    String inputBalance(String balanceId, String userid);
}
