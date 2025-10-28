package testing.demo.services;

import org.springframework.stereotype.Service;

import testing.demo.repository.TutorRepository;
import testing.demo.repository.CourseRepository;
import testing.demo.Classes.TutorRequest;
import testing.demo.Classes.exceptions.UserNotFoundException;

import java.util.UUID;
import java.util.List;
import testing.demo.Classes.Tutor;
import testing.demo.Classes.Course;

@Service
public class TutorService {
    private final TutorRepository tutorRepository;
    private final CourseRepository courseRepository;

    public TutorService(TutorRepository tutorRepository, CourseRepository courseRepository){
        this.tutorRepository = tutorRepository;
        this.courseRepository = courseRepository;
    }

    public List<Tutor> getAllTutors(){
        List<Tutor> tutors = tutorRepository.findAll();
        return tutors;
    }

    public Tutor createTutor(TutorRequest request){
        Tutor tutor = new Tutor();
        tutor.setUserUuid(request.getUser_id());
        tutor.setCourseList(request.getCourses());
        tutor.setRate(request.getRate());
        return tutorRepository.save(tutor);
    }
    
    public Tutor updateTutor(UUID id, TutorRequest request){
        Tutor tutor = tutorRepository.findById(id).orElseThrow(() -> new UserNotFoundException("tutor not found!"));
        if(request.getCourses() != null){
            tutor.setCourseList(request.getCourses());
        }
        tutor.setRate(request.getRate());

        return tutorRepository.save(tutor);
    }

    public void deleteTutor(UUID id){
        Tutor tutor = tutorRepository.findById(id).orElseThrow(() -> new UserNotFoundException("tutor not found!"));
        tutorRepository.delete(tutor);
    }
    
    

    /*
     * public List<Tutor> findTutorsByCourseName(String courseName){
        //check course exists
        UUID courseId = courseRepository.findByName(courseName).orElseThrow(() -> new RuntimeException("Course not found!") ).getId();
        return tutorRepository.findByCoursesContaining(courseId);
    }
     */
}
