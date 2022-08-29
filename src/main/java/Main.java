import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import DataSource.DataSource;
import Utils.JsonTransformer;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import model.Transaksi;
import model.User;
import org.sql2o.Sql2o;
import service.TransaksiServices;
import service.UserServices;

public class Main {
    public static void main(String[] args) {
//        Connection conn = db.connectToDb("minioy", "postgres", "130301");
//        db.insertUser(conn, "users", UUID.randomUUID().toString(), "akhdan", 100);

        //DI Implementation

        //check login before access the endpoint

        //refactor name of class dsb

        //postman documentation
        //end pointnya apa aja
        //response bentuknya gimana
        //

        //ubah key pakai underscore tanpa merubah model.java nya

        //bikin package untuk dao
        // - user dan transaksi sendiri

        //db transaction di post /transfer

        //service manggil dao

        //connect using hikari
        port(4567);

        get("/ping", ((request, response) -> "Aman Maszeh"));

        Sql2o sql2o = new Sql2o(DataSource.getMainDataSource());

        JsonTransformer jsonTransformer = new JsonTransformer();

        // Service User
        UserServices userService = new UserServices(sql2o);

        get("/users", (request, response) -> {
            response.status(200);
            response.type("application/json");

            return userService.getAllUser();
        }, jsonTransformer);

        post("/users", (request, response) -> {
            ObjectMapper mapper = new ObjectMapper();
            User creation = mapper.readValue(request.body(), User.class);

            String id = userService.insertUser(
                UUID.randomUUID().toString(),
                creation.getUsername(),
                creation.getSaldo()
            );

            response.status(200);
            response.type("application/json");
            return id;
        });

//        post("/login", (request, response) -> {
//            ObjectMapper mapper = new ObjectMapper();
//            User creation = mapper.readValue(request.body(), User.class);
//
//            String id = userService.insertUser(
//                UUID.randomUUID().toString(),
//                creation.getUsername(),
//                creation.getSaldo()
//            );
//
//            response.status(200);
//            response.type("application/json");
//            return id;
//        });

        //Service Transfer
        TransaksiServices transaksiService = new TransaksiServices(sql2o);
        get("/transfer", (request, response) -> {
            response.status(200);
            response.type("application/json");

            return transaksiService.getAllTransfer();
        }, jsonTransformer);

        post("/transfer", (request, response) -> {
            ObjectMapper mapper = new ObjectMapper();
            Transaksi creation = mapper.readValue(request.body(), Transaksi.class);

            String id = transaksiService.doTransfer(
                UUID.randomUUID().toString(),
                creation.getIdPengirim(),
                creation.getIdPenerima(),
                creation.getJumlahUang()
            );

            transaksiService.updateSaldoUserPengirim(
                creation.getIdPengirim(),
                creation.getJumlahUang()
            );
            transaksiService.updateSaldoUserPenerima(
                creation.getIdPenerima(),
                creation.getJumlahUang()
            );

            response.status(200);
            response.type("application/json");
            return id;
        });

        get("/saldofromuser", (request, response) -> {
            response.status(200);
            response.type("application/json");

            return transaksiService.getDataPengirim("transaksi1");
        }, jsonTransformer);
    }
}
