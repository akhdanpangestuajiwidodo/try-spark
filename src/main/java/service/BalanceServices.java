package service;

import dao.BalanceDao;
import javax.inject.Inject;

public class BalanceServices {
    private BalanceDao balanceDao;

    @Inject
    public BalanceServices(BalanceDao balanceDao){
        this.balanceDao = balanceDao;
    }

    public int getBalance(String userId){
        return balanceDao.getBalance(userId);
    }

    public int updateBalance(String userId){
        return balanceDao.updateBalance(userId);
    }
}
