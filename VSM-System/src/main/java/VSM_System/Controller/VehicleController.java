package VSM_System.Controller;

import VSM_System.Util.VarList;
import VSM_System.dto.ResponseDTO;
import VSM_System.dto.VehicleDTO;
import VSM_System.repo.VehicleRepo;
import VSM_System.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "api/v1/vehicle")
@CrossOrigin(origins = "http://localhost:5173")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private ResponseDTO responseDTO;
    @Autowired
    private VehicleRepo vehicleRepo;

    @GetMapping("/getAllVehicle")
    public ResponseEntity getAllVehicle() {
        try{
            List<VehicleDTO> vehicleDTOList = vehicleService.getAllVehicle();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Vehicle saved successfully");
            responseDTO.setContent(vehicleDTOList);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

        }catch (Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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

    @PutMapping("/updateVehicle")
    public ResponseEntity updateVehicle(@RequestBody VehicleDTO vehicleDTO) {
            String vres = vehicleService.updateVehicle(vehicleDTO);
            try {
                if (vres.equals("00")) {
                    responseDTO.setCode(VarList.RSP_SUCCESS);
                    responseDTO.setMessage("Vehicle updated successfully");
                    responseDTO.setContent(vehicleDTO);
                    return new ResponseEntity(vehicleDTO, HttpStatus.ACCEPTED);
                } else {
                    responseDTO.setCode(VarList.RSP_ERROR);
                    responseDTO.setMessage("Error occured");
                    responseDTO.setContent(null);
                    return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
                }
            } catch (Exception e) {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage(e.getMessage());
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
    @DeleteMapping("/deleteVehicle/{id}")
    public ResponseEntity<ResponseDTO> deleteVehicle(@PathVariable String id) {
        try {
            if (vehicleRepo.existsById(id)) {
                vehicleRepo.deleteById(id);
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Vehicle deleted successfully");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Vehicle not found");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getVehicleById/{vehicle_id}")
    public ResponseEntity getVehicleById(@PathVariable String vehicle_id) {
        try {
            VehicleDTO vehicleDTO = vehicleService.getVehicleById(vehicle_id);
            if (vehicleDTO != null) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Vehicle retrieved successfully");
                responseDTO.setContent(vehicleDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("No vehicle found for the provided ID");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
