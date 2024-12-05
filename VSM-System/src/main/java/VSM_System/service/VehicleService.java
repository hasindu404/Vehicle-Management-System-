package VSM_System.service;

import VSM_System.Util.VarList;
import VSM_System.dto.UserDTO;
import VSM_System.dto.VehicleDTO;
import VSM_System.entity.Vehicle;
import VSM_System.repo.VehicleRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class VehicleService {

    @Autowired
    private VehicleRepo vehicleRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<VehicleDTO> getVehicle(VehicleDTO vehicleDTO) {
        List<Vehicle> vehicleList = vehicleRepo.findAll();
        return modelMapper.map(vehicleList,new TypeToken<List<VehicleDTO>>(){}.getType());
    }

    public String saveVehicle(VehicleDTO vehicleDTO) {
        if (vehicleRepo.existsById(vehicleDTO.getVehicle_id())) {
            return VarList.RSP_DUPLICATED;
        } else {
            vehicleRepo.save(modelMapper.map(vehicleDTO, Vehicle.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public String updateVehicle(VehicleDTO vehicleDTO) {
        vehicleRepo.save(modelMapper.map(vehicleDTO, Vehicle.class));
        return VarList.RSP_SUCCESS;
    }

    public boolean deleteVehicle(VehicleDTO vehicleDTO) {
        vehicleRepo.delete(modelMapper.map(vehicleDTO , Vehicle.class));
        return true;
    }
}
