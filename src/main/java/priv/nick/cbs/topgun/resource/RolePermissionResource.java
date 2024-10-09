package priv.nick.cbs.topgun.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.nick.cbs.topgun.model.RolePermission;

@RestController
@RequestMapping("/role_permission")
public class RolePermissionResource {
    @GetMapping("/permission/all")
    public ResponseEntity<?> getAllPermission(){
        return null;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRolePermission(){
        return null;
    }

    public ResponseEntity<?> modifyRolePermission(){
        return null;
    }
}
