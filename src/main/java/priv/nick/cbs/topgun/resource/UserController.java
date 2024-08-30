package priv.nick.cbs.topgun.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import priv.nick.cbs.topgun.dto.response.ResponseInfo;
import priv.nick.cbs.topgun.dto.user.UserAddDTO;
import priv.nick.cbs.topgun.dto.user.UserInfoDTO;
import priv.nick.cbs.topgun.dto.user.UserUpdateDTO;
import priv.nick.cbs.topgun.service.UserService;

import java.util.List;

/**
 * @author nick.sui
 * @since 2024-08-28
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<?> add(@RequestBody @Validated UserAddDTO user){
        Long id = userService.createUser(user);
        return ResponseEntity.ok(new ResponseInfo("success", id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Validated UserUpdateDTO user,
                                    @PathVariable Long id){
        userService.updateUser(user,id);
        return ResponseEntity.ok(new ResponseInfo("success"));
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> findByUserName(@PathVariable String name){
        UserInfoDTO userInfoDTO = userService.findByUsername(name);
        return ResponseEntity.ok(new ResponseInfo("success",userInfoDTO));
    }

    @GetMapping("/role/{roleId}")
    public ResponseEntity<?> findAll(@PathVariable Long roleId){
        List<UserInfoDTO> userInfoDTOList = userService.findByRole(roleId);
        return ResponseEntity.ok(new ResponseInfo("success",userInfoDTOList));
    }
}
