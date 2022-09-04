package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import helper.JsonTransformer;
import javax.inject.Inject;
import model.Balance;
import service.BalanceServices;

public class BalanceController extends AbstractController{
    private final BalanceServices balanceServices;
    private final JsonTransformer jsonTransformer;
    private final ObjectMapper objectMapper;

    @Inject
    public BalanceController(BalanceServices balanceServices, JsonTransformer jsonTransformer,
                               ObjectMapper objectMapper) {
        this.balanceServices = balanceServices;
        this.jsonTransformer = jsonTransformer;
        this.objectMapper = objectMapper;
    }

    @Override
    public void registerApi() {

        get("/users/balance/:userid", (request, response) -> {
            response.status(200);
            response.type("application/json");

            return balanceServices.getBalance(request.params(":userid"));
        }, jsonTransformer);

        post("/balance", (request, response) -> {
            Balance creation = objectMapper.readValue(request.body(), Balance.class);

            String id = balanceServices.inputBalance(creation.getUserId(), creation.getType());

            response.status(200);
            response.type("application/json");
            return id;
        });

        post("/balance/topup", (request, response) -> {
            Balance creation = objectMapper.readValue(request.body(), Balance.class);

            String amount = balanceServices.updateBalance(creation.getAmount(), creation.getType(), creation.getUserId());

            response.status(200);
            response.type("application/json");
            return amount;
        });

    }
}
