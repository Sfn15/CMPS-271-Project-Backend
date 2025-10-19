package testing.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testing.demo.Classes.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByCode(String code);
}
