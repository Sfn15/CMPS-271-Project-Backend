package testing.demo.Classes;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String code;

    private Integer credits;

    @Column(columnDefinition = "text")
    private String description;

    private String department;

    public Course() {}

    public Course(String title, String code, Integer credits, String description, String department) {
        this.title = title;
        this.code = code;
        this.credits = credits;
        this.description = description;
        this.department = department;
    }

    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public Integer getCredits() { return credits; }
    public void setCredits(Integer credits) { this.credits = credits; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}
