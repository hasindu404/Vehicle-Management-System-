package VSM_System.Controller;

import VSM_System.Util.VarList;
import VSM_System.dto.AppointmentDTO;
import VSM_System.dto.ResponseDTO;
import VSM_System.entity.Appointment;
import VSM_System.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/appointment")
public class AppointmentController {

    @Autowired
    public AppointmentService appointmentService;

    @Autowired
    public ResponseDTO responseDTO;

    @PostMapping("/saveAppointment")
    public ResponseEntity saveAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        try {
            String ares = appointmentService.saveAppointment(appointmentDTO);
            if(ares.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Appointment Saved Successfully");
                responseDTO.setContent(appointmentDTO);
                return new ResponseEntity(appointmentDTO, HttpStatus.ACCEPTED);
            } else if (ares.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Appointment Already Exists");
                responseDTO.setContent(appointmentDTO);
                return new ResponseEntity(appointmentDTO, HttpStatus.BAD_REQUEST);
            }else{
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

    @PutMapping("/updateAppointment")
    public ResponseEntity updateAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        try {
            String ares = appointmentService.updateAppointment(appointmentDTO);
            if(ares.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Appointment Updated Successfully");
                responseDTO.setContent(appointmentDTO);
                return new ResponseEntity(appointmentDTO, HttpStatus.ACCEPTED);
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

    @DeleteMapping("/deleteAppointment")
    public boolean deleteAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        appointmentService.deleteAppointment(appointmentDTO);
        return true;
    }
}
