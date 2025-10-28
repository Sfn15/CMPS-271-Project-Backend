package testing.demo.Classes;
import java.util.UUID;
public class TutorRequest {
    String email;
    UUID[] courses;
    UUID user_id;
    int rate;

    public TutorRequest(String email, UUID[] courses, UUID user_id, int rate){
        this.email = email;
        this.courses = courses;
        this.user_id = user_id;
        this.rate = rate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID[] getCourses() {
        return courses;
    }

    public void setCourses(UUID[] courses) {
        this.courses = courses;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    
}
