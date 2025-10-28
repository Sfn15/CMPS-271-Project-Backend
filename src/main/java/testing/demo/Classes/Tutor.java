package testing.demo.Classes;
import java.util.UUID;
import java.util.LinkedList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "Tutors table")
public class Tutor {
    private final int MAX_COURSE_COUNT = 20;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private UUID userUuid;
    private UUID[] courses;
    private int rate;

    public Tutor(){
        this.courses = new UUID[MAX_COURSE_COUNT];
    }
    public Tutor(UUID id, UUID userUuid, int rate){
        this.id = id;
        this.userUuid = userUuid;
        this.courses = new UUID[MAX_COURSE_COUNT];
        this.rate = rate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(UUID userUuid) {
        this.userUuid = userUuid;
    }

    public UUID[] getCourseList() {
        return courses;
    }

    public void setCourseList(UUID[] courses) {
        this.courses = courses;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
