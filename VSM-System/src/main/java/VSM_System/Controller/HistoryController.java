package VSM_System.Controller;

import VSM_System.Util.VarList;
import VSM_System.dto.ResponseDTO;
import VSM_System.dto.ServiceHistoryDTO;
import VSM_System.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/history")
@CrossOrigin

public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/saveHistory")
    public ResponseEntity saveHistory(@RequestBody ServiceHistoryDTO serviceHistoryDTO) {
        try{
            String hres = historyService.saveServiceHistory(serviceHistoryDTO);
            if(hres.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("History Saved Successfully");
                responseDTO.setContent(serviceHistoryDTO);
                return new ResponseEntity(serviceHistoryDTO, HttpStatus.ACCEPTED);
            } else if (hres.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("History Already exist");
                responseDTO.setContent(serviceHistoryDTO);
                return new ResponseEntity(serviceHistoryDTO, HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("History Saving Failed");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("History Saving Failed");
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateServiceHistory")
    public ResponseEntity updateServiceHistory(@RequestBody ServiceHistoryDTO serviceHistoryDTO) {
        try {
            String hres = historyService.updateServiceHistory(serviceHistoryDTO);
            if(hres.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("History Updated Successfully");
                responseDTO.setContent(serviceHistoryDTO);
                return new ResponseEntity(serviceHistoryDTO, HttpStatus.ACCEPTED);
            }else{
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("History Updated Failed");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("History Updated Failed");
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteHistory")
    public boolean deleteHistory(@RequestBody ServiceHistoryDTO serviceHistoryDTO) {
        return historyService.deleteServiceHistory(serviceHistoryDTO);
    }
}
