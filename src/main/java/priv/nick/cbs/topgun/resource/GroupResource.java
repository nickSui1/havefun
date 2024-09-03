package priv.nick.cbs.topgun.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
public class GroupResource {

    @PostMapping("/")
    public ResponseEntity<?> add(){
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id){
        return null;
    }

    @GetMapping("/list")
    public ResponseEntity<?> findAll(){
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return null;
    }
}
