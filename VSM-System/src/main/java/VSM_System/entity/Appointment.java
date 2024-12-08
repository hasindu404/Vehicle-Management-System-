package VSM_System.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Appointment {
    @Id
    public String appointment_Id;
    public String userID;
    public String name;
    public String email;
    public Time time;
    public String service_type;
}
