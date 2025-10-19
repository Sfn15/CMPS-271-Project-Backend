package testing.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import testing.demo.Classes.Document;
import testing.demo.repository.DocumentRepository;
import testing.demo.repository.CourseRepository;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final CourseRepository courseRepository;

    public DocumentService(DocumentRepository documentRepository, CourseRepository courseRepository) {
        this.documentRepository = documentRepository;
        this.courseRepository = courseRepository;
    }

    public Document uploadDocument(MultipartFile file, Long courseId) throws IOException {
        String uploadDir = System.getProperty("user.dir") + "/uploads/documents/";
        Files.createDirectories(Paths.get(uploadDir));

        String filePath = uploadDir + file.getOriginalFilename();
        file.transferTo(new File(filePath));

        Document document = new Document();
        document.setName(file.getOriginalFilename());
        document.setPath(filePath);
        document.setCourse(courseRepository.findById(courseId).orElseThrow());
        document.setApproved(false); // not approved yet

        return documentRepository.save(document);
    }

    public List<Document> getApprovedDocuments(Long courseId) {
        return documentRepository.findByCourseIdAndApprovedTrue(courseId);
    }
}
