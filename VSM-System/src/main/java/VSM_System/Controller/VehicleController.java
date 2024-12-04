package VSM_System.Controller;

import VSM_System.Util.VarList;
import VSM_System.dto.ResponseDTO;
import VSM_System.dto.VehicleDTO;
import VSM_System.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/vehicle")
@CrossOrigin
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/saveVehicle")
    public ResponseEntity saveVehicle(@RequestBody VehicleDTO vehicleDTO) {

        try {
            String vres = vehicleService.saveVehicle(vehicleDTO);
            if (vres.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Vehicle saved successfully");
                responseDTO.setContent(vehicleDTO);
                return new ResponseEntity(vehicleDTO, HttpStatus.ACCEPTED);
            } else if (vres.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Vehicle already exists");
                responseDTO.setContent(vehicleDTO);
                return new ResponseEntity(vehicleDTO, HttpStatus.BAD_REQUEST);
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

}
