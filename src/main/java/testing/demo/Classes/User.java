package testing.demo.Classes;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //makes id auto increment
    private UUID id;
    private String name;
    private String email;
    private String phone_number;

    @Column(name = "password")
    private String password;

    public String getPassword(){
        return password;
    }
    public void setPassword(String pword){
        this.password = pword;
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone_number() {
        return phone_number;
    }
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    
}
