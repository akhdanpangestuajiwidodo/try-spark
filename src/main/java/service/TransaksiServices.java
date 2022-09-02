package service;

import dao.TranasaksiDao;
import dao.TransaksiDaoImpl;
import java.util.List;
import javax.inject.Inject;
import model.Transaksi;

public class TransaksiServices {
    private final TranasaksiDao transaksiDao;

    @Inject
    public TransaksiServices(TranasaksiDao transaksiDao) {
        this.transaksiDao = transaksiDao;
    }

    public List<Transaksi> getAllTransfer() {
        return transaksiDao.getAllTransfer();
    }

    public int doTransfer(
        String transaksiId,
        String idPengirim,
        String idPenerima,
        int jumlahUang
    ) {
        return transaksiDao.doTransfer(transaksiId, idPengirim, idPenerima, jumlahUang);
    }

    public int getDataPengirim(String idPengirim) {
        return transaksiDao.getDataPengirim(idPengirim);
    }

    public Transaksi getSpecificTransfer(String transaksiId) {
        return transaksiDao.getSpecificTransfer(transaksiId);
    }
}
