package VSM_System.service;

import VSM_System.dto.UserDTO;
import VSM_System.entity.User;
import VSM_System.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO saveUser(UserDTO userDTO){
            userRepo.save(modelMapper.map(userDTO, User.class));
            return userDTO;
    }
}
