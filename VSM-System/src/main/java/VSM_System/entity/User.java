package VSM_System.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class User {
    @Id
    public String userID;
    public String fName;
    public String lName;
    public String address;
    public int phone;
    public String email;
    public String password;
    public String role;
}
