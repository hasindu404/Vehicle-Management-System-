package VSM_System.service;

import VSM_System.Util.VarList;
import VSM_System.dto.VehicleDTO;
import VSM_System.entity.Vehicle;
import VSM_System.repo.VehicleRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class VehicleService {

    @Autowired
    private VehicleRepo vehicleRepo;

    @Autowired
    private ModelMapper modelMapper;


    public String saveVehicle(VehicleDTO vehicleDTO) {
        if (vehicleRepo.existsById(vehicleDTO.getVehicle_id())) {
            return VarList.RSP_DUPLICATED;
        } else {
            vehicleRepo.save(modelMapper.map(vehicleDTO, Vehicle.class));
            return VarList.RSP_SUCCESS;
        }
    }
}
