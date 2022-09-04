package dao;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import model.Balance;
import model.Transaksi;
import model.User;
import model.UserResponse;
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
    public int doTransfer(String idPengirim, String idPenerima,
                             int jumlahUang) {
        UUID transaksiId = UUID.randomUUID();
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
                    "update balance set amount = (amount + :jumlahUang) where \"userId\" = :userid")
                .addParameter("jumlahUang", jumlahUang)
                .addParameter("userid", idPenerima)
                .executeUpdate();

            connection.createQuery(
                    "update balance set amount = (amount - :jumlahUang) where \"userId\" = :userid")
                .addParameter("jumlahUang", jumlahUang)
                .addParameter("userid", idPengirim)
                .executeUpdate();

            Balance balanceUser = connection.createQuery(
                "select amount from balance where \"userId\" = :userid")
                .addParameter("userid", idPengirim)
                .executeAndFetchFirst(Balance.class);

            connection.commit();

            return balanceUser.getAmount();

        } catch (Exception e) {
            System.out.println(e);
        }

        return 0;
    }

    @Override
    public int getDataPengirim(String idPengirim) {
        try (org.sql2o.Connection conn = sql2o.open()) {
            User saldoUser =
                conn.createQuery("SELECT saldo from users where userid = :userid")
                    .addParameter("userid", idPengirim)
                    .executeAndFetchFirst(User.class);

            return 0;
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
