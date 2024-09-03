package priv.nick.cbs.topgun.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import priv.nick.cbs.topgun.model.SetMenu;
import priv.nick.cbs.topgun.dto.response.ResponseInfo;
import priv.nick.cbs.topgun.dto.setmenu.SetMenuAddDTO;
import priv.nick.cbs.topgun.dto.setmenu.SetMenuInfoDTO;
import priv.nick.cbs.topgun.service.SetMenuService;

import java.util.List;

@RestController
@RequestMapping("/set_menu")
public class SetMenuResource {
    private SetMenuService setMenuService;
    @Autowired
    public SetMenuResource(SetMenuService setMenuService) {
        this.setMenuService=setMenuService;
    }

    @GetMapping("/list")
    public ResponseEntity<?> selectAll(){
        try{
            List<SetMenu> setMenus = setMenuService.getSetMenus();
            return ResponseEntity.ok(new ResponseInfo( "success", setMenus));
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseInfo("error"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id ){
        SetMenuInfoDTO setMenuInfoDTO = setMenuService.findById(Long.parseLong(id));
        return ResponseEntity.ok(new ResponseInfo( "success", setMenuInfoDTO));
    }

    @PostMapping("/")
    public ResponseEntity<?> add(@RequestBody @Validated SetMenuAddDTO setMenuDTO){
        Long id = setMenuService.add(setMenuDTO);
        return ResponseEntity.ok(new ResponseInfo("success", id));
    }
}
