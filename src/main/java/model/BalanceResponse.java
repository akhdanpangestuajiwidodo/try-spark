package model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class BalanceResponse {
    private String userId;
    @JsonAlias({"user_name", "userName"})
    private String username;
    @JsonAlias("amount")
    private int amount;
    private boolean status;
}
