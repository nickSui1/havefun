package priv.nick.cbs.topgun.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/property")
public class PropertyResource {
    @PostMapping("/")
    public ResponseEntity<?> add(){
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id){
        return null;
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(){
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return null;
    }
}
