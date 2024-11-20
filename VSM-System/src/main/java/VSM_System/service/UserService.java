package VSM_System.service;

import VSM_System.Response.LoginResponse;
import VSM_System.Util.VarList;
import VSM_System.dto.LoginDTO;
import VSM_System.dto.UserDTO;
import VSM_System.entity.User;
import VSM_System.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String saveUser(UserDTO userDTO){
        if(userRepo.existsById(userDTO.getUserID())){
            return VarList.RSP_DUPLICATED;
        }else {
            userRepo.save(modelMapper.map(userDTO, User.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public LoginResponse loginUser(LoginDTO loginDTO) {
        String msg = "";
        User user1 = userRepo.findByEmail(loginDTO.getEmail());
        if(user1 != null){
            String password = loginDTO.getPassword();
            boolean isPwdRight = password.equals(user1.getPassword());
            if(isPwdRight){
                Optional<User> user = userRepo.findOneByEmailAndPassword(loginDTO.getEmail(), password);
                if(user.isPresent()){
                    return new LoginResponse("Login Success",true);
                }else{
                    return new LoginResponse("Login Failed",false);
                }
            }else {
                return new LoginResponse("Password is not Match",false);
            }
        }else {
            return new LoginResponse("Email is not Match",false);
        }
    }
}
