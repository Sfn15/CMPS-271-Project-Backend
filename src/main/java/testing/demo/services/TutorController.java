package testing.demo.services;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import testing.demo.Classes.Tutor;
import testing.demo.Classes.TutorRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/tutors")
public class TutorController {
    private final TutorService tutorService;

    public TutorController(TutorService tutorService){
        this.tutorService = tutorService;
    }

    @PostMapping
    public ResponseEntity<Tutor> createTutor(@RequestBody TutorRequest request){
        return ResponseEntity.ok(tutorService.createTutor(request));
    }

    @GetMapping
    public ResponseEntity<List<Tutor>> getAllTutors(){
        return ResponseEntity.ok(tutorService.getAllTutors());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tutor> updateTutor(@PathVariable UUID id, @RequestBody TutorRequest request){
        Tutor tutor = tutorService.updateTutor(id, request);
        return ResponseEntity.ok(tutor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteTutor(@PathVariable UUID id){
        tutorService.deleteTutor(id);
        Map<String,String> map = new HashMap<>();
        map.put("message","Tutor deleted successfully");
        return ResponseEntity.ok(map);
    }

}
