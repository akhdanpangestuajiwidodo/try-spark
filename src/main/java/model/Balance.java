package model;

import lombok.Data;

@Data
public class Balance {
    private String balanceId;
    private String userId;
    private int amount;
}
