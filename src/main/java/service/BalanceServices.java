package service;

import dao.BalanceDao;
import javax.inject.Inject;

public class BalanceServices {
    private final BalanceDao balanceDao;

    @Inject
    public BalanceServices(BalanceDao balanceDao){
        this.balanceDao = balanceDao;
    }

    public int getBalance(String userId){
        return balanceDao.getBalance(userId);
    }

    public String inputBalance(String balanceId, String userId){return balanceDao.inputBalance(balanceId, userId);}

    public int updateBalance(String userId){
        return balanceDao.updateBalance(userId);
    }
}
