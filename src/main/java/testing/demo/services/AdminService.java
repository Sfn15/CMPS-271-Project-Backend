package testing.demo.services;

import org.springframework.stereotype.Service;
import testing.demo.repository.UserRepository;
import testing.demo.repository.DocumentRepository;
import testing.demo.repository.CourseRepository;
import testing.demo.Classes.Document;
import testing.demo.Classes.Course;
import testing.demo.Classes.User;

import java.util.List;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final DocumentRepository documentRepository;
    private final CourseRepository courseRepository;

    public AdminService(UserRepository userRepository, DocumentRepository documentRepository, CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.documentRepository = documentRepository;
        this.courseRepository = courseRepository;
    }

    // View all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // View all documents
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    // Approve a document
    public Document approveDocument(Long documentId) {
        Document doc = documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        doc.setApproved(true);
        return documentRepository.save(doc);
    }

    // Delete a course
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    // Delete a user
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}

