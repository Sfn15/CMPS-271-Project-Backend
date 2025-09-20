package testing.demo.Classes;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class User {
    private int id;
    private String name;
    private String email;
    private String phone_number;
    private String passsword;

    public String getPassword(){
        return passsword;
    }
    public void setPassword(String pword){
        this.passsword = pword;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
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
