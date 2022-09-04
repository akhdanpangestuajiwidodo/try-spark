package model;

import java.util.List;
import lombok.Data;

@Data
public class UserResponse {
    private User user;
    private List<Balance> listBalance;
}
