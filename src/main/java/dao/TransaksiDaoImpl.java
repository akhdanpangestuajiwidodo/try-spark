package dao;

import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import model.Transaksi;
import model.User;
import org.sql2o.Sql2o;

public class TransaksiDaoImpl implements TranasaksiDao {

    private final Sql2o sql2o;

    @Inject
    public TransaksiDaoImpl(Sql2o sql2o){
        this.sql2o = sql2o;
    }
    @Override
    public List<Transaksi> getAllTransfer() {
        try (org.sql2o.Connection conn = sql2o.open()) {
            return conn.createQuery("select * from transaksi").executeAndFetch(Transaksi.class);
        }
    }

    @Override
    public String doTransfer(String transaksiId, String idPengirim, String idPenerima,
                             int jumlahUang) {
        try (org.sql2o.Connection connection = sql2o.beginTransaction()) {
            connection.createQuery(
                    "insert into transaksi VALUES (:transaksiid, :idpengirim, :idpenerima, "
                        + ":jumlahuang, :tanggalTransaksi)")
                .addParameter("transaksiid", transaksiId)
                .addParameter("idpengirim", idPengirim)
                .addParameter("idpenerima", idPenerima)
                .addParameter("jumlahuang", jumlahUang)
                .addParameter("tanggalTransaksi", new Date())
                .executeUpdate();

            connection.createQuery(
                    "update users set saldo = (saldo + :jumlahUang) where userid = :userid")
                .addParameter("jumlahUang", jumlahUang)
                .addParameter("userid", idPenerima)
                .executeUpdate();

            connection.createQuery(
                    "update users set saldo = (saldo - :jumlahUang) where userid = :userid")
                .addParameter("jumlahUang", jumlahUang)
                .addParameter("userid", idPengirim)
                .executeUpdate();

            connection.commit();

            return transaksiId;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public int getDataPengirim(String idPengirim) {
        try (org.sql2o.Connection conn = sql2o.open()) {
            List<User> saldoUser =
                conn.createQuery("SELECT saldo from users where userid = :userid")
                    .addParameter("userid", idPengirim)
                    .executeAndFetch(User.class);

            return saldoUser.get(0).getSaldo();
        }
    }

    @Override
    public Transaksi getSpecificTransfer(String transaksiid) {
        try (org.sql2o.Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * from transaksi where transaksiid = :transaksiid")
                .addParameter("transaksiid", transaksiid)
                .executeAndFetchFirst(Transaksi.class);
        }
    }
}
