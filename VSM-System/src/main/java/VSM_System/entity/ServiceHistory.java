package VSM_System.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class ServiceHistory {
    @Id
    public String service_id;
    public String vehicle_id;
    public String service_type;
    public Date date;
    public String description;

}
