package testing.demo.repository;

import testing.demo.Classes.Tutor;
import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TutorRepository extends JpaRepository<Tutor,UUID>{
    Tutor findByEmal(String email);

    List<Tutor> findAll();


} 
