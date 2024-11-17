package VSM_System.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class UserDTO {

    public String userID;
    public String fName;
    public String lName;
    public String address;
    public int phone;
    public String email;
    public String password;
    public String role;
}
