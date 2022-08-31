package model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class User {
    private String userId;
    @JsonAlias({"user_name", "userName"})
    private String username;
    @JsonAlias("Saldo")
    private int saldo;
    private boolean status;
}
