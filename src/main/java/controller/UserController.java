package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import helper.JsonTransformer;
import java.util.UUID;
import javax.inject.Inject;
import model.User;
import service.BalanceServices;
import service.UserServices;

public class UserController extends AbstractController {

    private final UserServices userServices;
    private final BalanceServices balanceServices;
    private final JsonTransformer jsonTransformer;
    private final ObjectMapper objectMapper;

    @Inject
    public UserController(UserServices userServices, BalanceServices balanceServices, JsonTransformer jsonTransformer,
                          ObjectMapper objectMapper) {
        this.userServices = userServices;
        this.balanceServices = balanceServices;
        this.jsonTransformer = jsonTransformer;
        this.objectMapper = objectMapper;
    }

    @Override
    public void registerApi() {
        get("/users", (request, response) -> {
            response.status(200);
            response.type("application/json");
            return userServices.getAllUser();
        }, jsonTransformer);

        post("/users", (request, response) -> {
            User creation = objectMapper.readValue(request.body(), User.class);

            String id = userServices.insertUser(
                creation.getUsername()
            );

            balanceServices.inputBalance(id);

            response.status(200);
            response.type("application/json");
            return id;
        });

        post("/login", (request, response) -> {
            User creation = objectMapper.readValue(request.body(), User.class);

            String username = userServices.loginUser(creation.getUsername());
            User user = userServices.getStatusUser(username);

            response.status(200);
            response.type("application/json");
            return user.isStatus();
        });

    }
}
