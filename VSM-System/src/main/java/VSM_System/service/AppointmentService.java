package VSM_System.service;

import VSM_System.Util.VarList;
import VSM_System.dto.AppointmentDTO;
import VSM_System.entity.Appointment;
import VSM_System.repo.AppointmentRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AppointmentService {

    @Autowired
    public AppointmentRepo appointmentRepo;

    @Autowired
    public ModelMapper modelMapper;

    public String saveAppointment(AppointmentDTO appointmentDTO) {
        if(appointmentRepo.existsById(appointmentDTO.getAppointment_Id())){
            return VarList.RSP_DUPLICATED;
        }else{
            appointmentRepo.save(modelMapper.map(appointmentDTO, Appointment.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public String updateAppointment(AppointmentDTO appointmentDTO) {
        appointmentRepo.save(modelMapper.map(appointmentDTO, Appointment.class));
        return VarList.RSP_SUCCESS;
    }

    public boolean deleteAppointment(AppointmentDTO appointmentDTO) {
        appointmentRepo.delete(modelMapper.map(appointmentDTO, Appointment.class));
        return true;
    }

}
