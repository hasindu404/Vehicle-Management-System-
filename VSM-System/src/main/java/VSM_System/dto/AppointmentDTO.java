package VSM_System.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentDTO {
    public String appointment_Id;
    public String userID;
    public String name;
    public String email;
    public Time time;
    public String service_type;
}
