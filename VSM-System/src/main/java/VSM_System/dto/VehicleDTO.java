package VSM_System.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleDTO {

    public String vehicle_id;
    public String vehicle_make;
    public String vehicle_model;
    public Integer manufactured_year;
    public Integer registered_year;
    public String transmission_type;
    public String color;
}
