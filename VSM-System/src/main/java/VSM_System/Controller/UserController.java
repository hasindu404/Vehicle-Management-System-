package VSM_System.Controller;

import VSM_System.dto.UserDTO;
import VSM_System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/saveUser")
    public UserDTO saveUser(@RequestBody UserDTO userDTO){
        return userService.saveUser(userDTO);
    }

}
