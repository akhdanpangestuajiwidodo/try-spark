package model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class Balance {
    private String balanceId;
    @JsonAlias({"userid", "user_id"})
    private int amount;
    private String type;
    private String userId;
}
