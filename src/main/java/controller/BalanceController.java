package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import helper.JsonTransformer;
import javax.inject.Inject;
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

    }
}
