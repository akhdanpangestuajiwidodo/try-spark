package dao;

import java.util.List;
import model.Transaksi;

public interface TranasaksiDao {
    List<Transaksi> getAllTransfer();

    int doTransfer(String idPengirim, String idPenerima,
                   int jumlahUang);

    int getDataPengirim(String idPengirim);

    Transaksi getSpecificTransfer(String transaksiid);

}
