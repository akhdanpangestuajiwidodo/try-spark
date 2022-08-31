package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import helper.JsonTransformer;
import java.util.UUID;
import model.User;
import service.UserServices;

public class UserController {
    public UserController(UserServices userServices, JsonTransformer jsonTransformer,
                          ObjectMapper objectMapper) {
        get("/users", (request, response) -> {
            response.status(200);
            response.type("application/json");
            return userServices.getAllUser();
        }, jsonTransformer);

        post("/users", (request, response) -> {
            User creation = objectMapper.readValue(request.body(), User.class);

            String id = userServices.insertUser(
                UUID.randomUUID().toString(),
                creation.getUsername(),
                creation.getSaldo()
            );

            response.status(200);
            response.type("application/json");
            return id;
        });
    }
}
