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
    private String service_id;
    private String vehicle_id;
    private String service_type;
    private Date date;
    private String description;

}
