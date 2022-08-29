package dao;

import java.util.Date;
import java.util.List;
import model.Transaksi;
import model.User;
import org.sql2o.Sql2o;

public interface TranasaksiDao {
    List<Transaksi> getAllTransfer(Sql2o sql2o);

    String doTransfer(Sql2o sql2o, String transaksiId, String idPengirim, String idPenerima,
                      int jumlahUang);

    int getDataPengirim(Sql2o sql2o, String idPengirim);

    Transaksi getSpecificTransfer(Sql2o sql2o, String transaksiid);

    void updateSaldoUserPenerima(Sql2o sql2o, String userid, int besartransaksi);

    void updateSaldoUserPengirim(Sql2o sql2o, String userid, int besartransaksi);
}
