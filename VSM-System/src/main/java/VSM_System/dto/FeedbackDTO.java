package VSM_System.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeedbackDTO {
    private String feedback_id;
    private String userID;
    @JsonFormat(pattern = "yyyy/mm/dd")
    private Date submit_date;
    private String feedback_description;
}
