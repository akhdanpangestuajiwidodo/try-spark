package service;

import dao.TransaksiDaoImpl;
import java.util.List;
import javax.inject.Inject;
import model.Transaksi;
import org.sql2o.Sql2o;

public class TransaksiServices {
    private final TransaksiDaoImpl transaksiDao;
    private final Sql2o sql2o;


    @Inject
    public TransaksiServices(TransaksiDaoImpl transaksiDao, Sql2o sql2o) {
        this.transaksiDao = transaksiDao;
        this.sql2o = sql2o;
    }

    public List<Transaksi> getAllTransfer() {
        return transaksiDao.getAllTransfer(sql2o);
    }

    public String doTransfer(
        String transaksiId,
        String idPengirim,
        String idPenerima,
        int jumlahUang
    ) {
        return transaksiDao.doTransfer(sql2o, transaksiId, idPengirim, idPenerima, jumlahUang);
    }

    public int getDataPengirim(String idPengirim) {
        return transaksiDao.getDataPengirim(sql2o, idPengirim);
    }

    public Transaksi getSpecificTransfer(String transaksiId) {
        return transaksiDao.getSpecificTransfer(sql2o, transaksiId);
    }

    public void updateSaldoUserPenerima(String userid, int besartransaksi) {
        transaksiDao.updateSaldoUserPenerima(sql2o, userid, besartransaksi);
    }

    public void updateSaldoUserPengirim(String userid, int besartransaksi) {
        transaksiDao.updateSaldoUserPengirim(sql2o, userid, besartransaksi);
    }
}
