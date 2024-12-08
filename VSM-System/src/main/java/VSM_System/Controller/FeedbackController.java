package VSM_System.Controller;

import VSM_System.Util.VarList;
import VSM_System.dto.FeedbackDTO;
import VSM_System.dto.ResponseDTO;
import VSM_System.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="api/v1/feedback")
@CrossOrigin
public class FeedbackController {
    
    @Autowired
    private FeedbackService feedbackService;
    
    @Autowired
    private ResponseDTO responseDTO;
    
    @PostMapping("/saveFeedback")
    public ResponseEntity saveFeedback(FeedbackDTO feedbackDTO) {
        try {
            String fres = feedbackService.saveFeedback(feedbackDTO);
            if (fres.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Vehicle saved successfully");
                responseDTO.setContent(feedbackDTO);
                return new ResponseEntity(feedbackDTO, HttpStatus.ACCEPTED);
            } else if (fres.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Vehicle already exists");
                responseDTO.setContent(feedbackDTO);
                return new ResponseEntity(feedbackDTO, HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("Error Occurred");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateFeedback")
    public ResponseEntity updateFeedback(FeedbackDTO feedbackDTO) {
        try {
            String fres = feedbackService.updateFeedback(feedbackDTO);
            if (fres.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Vehicle updated successfully");
                responseDTO.setContent(feedbackDTO);
                return new ResponseEntity(feedbackDTO, HttpStatus.ACCEPTED);
            }else {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("Error occured");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public boolean deleteFeedback(FeedbackDTO feedbackDTO) {
        feedbackService.deleteFeedback(feedbackDTO);
        return true;
    }

}
