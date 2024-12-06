package VSM_System.service;

import VSM_System.Util.VarList;
import VSM_System.dto.ServiceHistoryDTO;
import VSM_System.entity.ServiceHistory;
import VSM_System.repo.ServiceHistoryRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HistoryService {

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    private ServiceHistoryRepo serviceHistoryRepo;

    public String saveServiceHistory(ServiceHistoryDTO serviceHistoryDTO) {
        if (serviceHistoryRepo.existsById(serviceHistoryDTO.getService_id())) {
            return VarList.RSP_DUPLICATED;
        } else {
            serviceHistoryRepo.save(modelMapper.map(serviceHistoryDTO, ServiceHistory.class));
            return VarList.RSP_SUCCESS;
        }
    }
    public String updateServiceHistory(ServiceHistoryDTO serviceHistoryDTO) {
        serviceHistoryRepo.save(modelMapper.map(serviceHistoryDTO, ServiceHistory.class));
        return VarList.RSP_SUCCESS;
    }
    public boolean deleteServiceHistory(ServiceHistoryDTO serviceHistoryDTO) {
        serviceHistoryRepo.delete(modelMapper.map(serviceHistoryDTO, ServiceHistory.class));
        return true;
    }
}
