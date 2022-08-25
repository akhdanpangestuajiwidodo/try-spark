package dao;

import java.util.Date;
import java.util.List;
import model.Transaksi;
import model.User;
import org.sql2o.Sql2o;

public class TransaksiDaoImplementation implements TranasaksiDao {
    @Override
    public List<Transaksi> getAllTransfer(Sql2o sql2o) {
        try (org.sql2o.Connection conn = sql2o.open()) {
            return conn.createQuery("select * from transaksi").executeAndFetch(Transaksi.class);
        }
    }

    @Override
    public String doTransfer(Sql2o sql2o, String transaksiId, String idPengirim, String idPenerima,
                             int jumlahUang) {
        try (org.sql2o.Connection connection = sql2o.open()) {
            connection.createQuery(
                    "insert into transaksi VALUES (:transaksiid, :idpengirim, :idpenerima, "
                        + ":jumlahuang, :tanggalTransaksi)")
                .addParameter("transaksiid", transaksiId)
                .addParameter("idpengirim", idPengirim)
                .addParameter("idpenerima", idPenerima)
                .addParameter("jumlahuang", jumlahUang)
                .addParameter("tanggalTransaksi", new Date())
                .executeUpdate();

            return transaksiId;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public int getDataPengirim(Sql2o sql2o, String idPengirim) {
        try (org.sql2o.Connection conn = sql2o.open()) {
            List<User> saldoUser =
                conn.createQuery("SELECT saldo from users where userid = :userid")
                    .addParameter("userid", idPengirim)
                    .executeAndFetch(User.class);

            return saldoUser.get(0).getSaldo();
        }
    }

    @Override
    public Transaksi getSpecificTransfer(Sql2o sql2o, String transaksiid) {
        try (org.sql2o.Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * from transaksi where transaksiid = :transaksiid")
                .addParameter("transaksiid", transaksiid)
                .executeAndFetchFirst(Transaksi.class);
        }
    }

    @Override
    public void updateSaldoUserPenerima(Sql2o sql2o, String userid, int besartransaksi) {
        try (org.sql2o.Connection connection = sql2o.open()) {
            connection.createQuery(
                    "update users set saldo = (saldo + :besartransaksi) where userid = :userid")
                .addParameter("besartransaksi", besartransaksi)
                .addParameter("userid", userid)
                .executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void updateSaldoUserPengirim(Sql2o sql2o, String userid, int besartransaksi) {
        try (org.sql2o.Connection connection = sql2o.open()) {

            connection.createQuery(
                    "update users set saldo = (saldo - :besartransaksi) where userid = :userid")
                .addParameter("besartransaksi", besartransaksi)
                .addParameter("userid", userid)
                .executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
