package dao;

import java.util.Date;
import java.util.List;
import model.Transaksi;

public interface TranasaksiDao {
    List<Transaksi> getAllTransfer();

    String doTransfer(String transaksiId, String idPengirim, String idPenerima,
                      int jumlahUang);

    int getDataPengirim(String idPengirim);

    Transaksi getSpecificTransfer(String transaksiid);

    void updateSaldoUserPenerima(String userid, int besartransaksi);

    void updateSaldoUserPengirim(String userid, int besartransaksi);
}
