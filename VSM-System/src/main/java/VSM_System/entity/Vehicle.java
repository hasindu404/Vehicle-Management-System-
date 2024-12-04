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
public class Vehicle {
    @Id
    public String vehicle_id;
    public String vehicle_make;
    public String vehicle_model;
    public Integer manufactured_year;
    public Integer registered_year;
    public String transmission_type;
    public String color;
}
