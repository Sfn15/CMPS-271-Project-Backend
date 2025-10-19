package testing.demo.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import testing.demo.services.AdminService;
import testing.demo.Classes.Document;
import testing.demo.Classes.User;
import testing.demo.Classes.Course;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Get all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    // Get all documents
    @GetMapping("/documents")
    public List<Document> getAllDocuments() {
        return adminService.getAllDocuments();
    }

    @PutMapping("/documents/{id}/approve")
public ResponseEntity<String> approveDocument(@PathVariable Long id) {
    adminService.approveDocument(id);
    return ResponseEntity.ok("Document approved successfully");
}


    // Delete course
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        adminService.deleteCourse(id);
        return ResponseEntity.ok("Course deleted successfully.");
    }

    // Delete user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully.");
    }
}

