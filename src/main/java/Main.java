import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.port;
import static spark.Spark.post;

import controller.PingController;
import controller.UserController;
import helper.JsonTransformer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.UUID;
import model.Transaksi;
import model.User;
import service.TransaksiServices;
import service.UserServices;

public class Main {
    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new AppModule());
        UserServices userService = injector.getInstance(UserServices.class);
        TransaksiServices transaksiService = injector.getInstance(TransaksiServices.class);
        ObjectMapper mapper = injector.getInstance(ObjectMapper.class);
        JsonTransformer jsonTransformer = injector.getInstance(JsonTransformer.class);

        //connect using hikari
        port(4567);

        before(((request, response) -> {
            boolean isNotLogin = false;
            if(isNotLogin){
                halt(401, "Harus login dulu");
            }
        }));

        new PingController();
        new UserController(userService, jsonTransformer, mapper);

        //Service Transfer
        get("/transfer", (request, response) -> {
            response.status(200);
            response.type("application/json");

            return transaksiService.getAllTransfer();
        }, jsonTransformer);

        post("/transfer", (request, response) -> {
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
