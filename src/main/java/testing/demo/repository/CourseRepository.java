package testing.demo.repository;

import testing.demo.Classes.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<String,UUID>{
    Optional<Course> findByName(String name);
}
