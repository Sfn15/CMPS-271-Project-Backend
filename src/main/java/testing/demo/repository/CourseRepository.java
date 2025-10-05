package testing.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import testing.demo.Classes.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByCode(String code);
}