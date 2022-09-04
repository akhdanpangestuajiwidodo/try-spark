package service;

import dao.BalanceDao;
import java.util.List;
import javax.inject.Inject;
import model.Balance;

public class BalanceServices {
    private final BalanceDao balanceDao;

    @Inject
    public BalanceServices(BalanceDao balanceDao){
        this.balanceDao = balanceDao;
    }

    public List<Balance> getBalance(String userId){
        return balanceDao.getBalance(userId);
    }

    public String inputBalance(String userId, String type){return balanceDao.inputBalance(userId, type);}

    public String updateBalance(int amount, String type, String userId){
        return balanceDao.topUpBalance(amount, type, userId);
    }
}
