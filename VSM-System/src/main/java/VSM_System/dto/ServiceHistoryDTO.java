package VSM_System.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceHistoryDTO {

    private String service_id;
    private String vehicle_id;
    private String service_type;
    @JsonFormat(pattern = "yyyy/mm/dd")
    private Date date;
    private String description;
}
