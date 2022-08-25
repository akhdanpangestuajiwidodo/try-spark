package dao;

import java.util.Date;
import java.util.List;
import model.Transaksi;
import model.User;
import org.sql2o.Sql2o;

public interface TranasaksiDao {
    public List<Transaksi> getAllTransfer(Sql2o sql2o);

    public String doTransfer(Sql2o sql2o, String transaksiId, String idPengirim, String idPenerima,
                             int jumlahUang);

    public int getDataPengirim(Sql2o sql2o, String idPengirim);

    public Transaksi getSpecificTransfer(Sql2o sql2o, String transaksiid);

    public void updateSaldoUserPenerima(Sql2o sql2o, String userid, int besartransaksi);

    public void updateSaldoUserPengirim(Sql2o sql2o, String userid, int besartransaksi);
}
