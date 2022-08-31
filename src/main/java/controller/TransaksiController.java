package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import helper.JsonTransformer;
import java.util.UUID;
import model.Transaksi;
import service.TransaksiServices;

public class TransaksiController {
    public TransaksiController(TransaksiServices transaksiServices, JsonTransformer jsonTransformer,
                               ObjectMapper objectMapper) {
        get("/transfer", (request, response) -> {
            response.status(200);
            response.type("application/json");

            return transaksiServices.getAllTransfer();
        }, jsonTransformer);

        post("/transfer", (request, response) -> {
            Transaksi creation = objectMapper.readValue(request.body(), Transaksi.class);

            String id = transaksiServices.doTransfer(
                UUID.randomUUID().toString(),
                creation.getIdPengirim(),
                creation.getIdPenerima(),
                creation.getJumlahUang()
            );

            transaksiServices.updateSaldoUserPengirim(
                creation.getIdPengirim(),
                creation.getJumlahUang()
            );
            transaksiServices.updateSaldoUserPenerima(
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

            return transaksiServices.getDataPengirim("transaksi1");
        }, jsonTransformer);
    }
}
