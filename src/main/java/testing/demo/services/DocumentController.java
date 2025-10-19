package testing.demo.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import testing.demo.Classes.Document;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/upload/{courseId}")
    public ResponseEntity<?> uploadDocument(@RequestParam("file") MultipartFile file,
                                            @PathVariable Long courseId) {
        try {
            Document doc = documentService.uploadDocument(file, courseId);
            return ResponseEntity.ok("Document uploaded successfully: " + doc.getName());
        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body("Upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/approved/{courseId}")
    public List<Document> getApprovedDocuments(@PathVariable Long courseId) {
        return documentService.getApprovedDocuments(courseId);
    }
}
