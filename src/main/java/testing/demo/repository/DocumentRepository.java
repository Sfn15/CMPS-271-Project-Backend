package testing.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import testing.demo.Classes.Document;
import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByCourseIdAndApprovedTrue(Long courseId);
}
