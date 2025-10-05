package testing.demo.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import testing.demo.Classes.Course;
import testing.demo.Classes.CourseCreateRequest;
import testing.demo.repository.CourseRepository;

import java.net.URI;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class CourseController {

    private final CourseRepository courses;

    public CourseController(CourseRepository courses) {
        this.courses = courses;
    }

   
    @PostMapping("/courses")
    public ResponseEntity<?> createCourse(@RequestBody CourseCreateRequest req) {
        
        if (req.getTitle() == null || req.getTitle().isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "title is required"));
        }
        if (req.getCode() == null || req.getCode().isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "code is required"));
        }
        if (courses.existsByCode(req.getCode())) {
            return ResponseEntity.badRequest().body(Map.of("error", "course code already exists"));
        }

        Course saved = courses.save(new Course(
                req.getTitle(),
                req.getCode(),
                req.getCredits(),
                req.getDescription(),
                req.getDepartment()
        ));

        return ResponseEntity
                .created(URI.create("/api/courses/" + saved.getId()))
                .body(Map.of(
                        "id", saved.getId(),
                        "title", saved.getTitle(),
                        "code", saved.getCode(),
                        "credits", saved.getCredits(),
                        "description", saved.getDescription(),
                        "department", saved.getDepartment()
                ));
    }

    
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        if (!courses.existsById(id)) {
            throw new NoSuchElementException("Course not found: " + id);
        }
        courses.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> notFound(NoSuchElementException e) {
        return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
    }
}

