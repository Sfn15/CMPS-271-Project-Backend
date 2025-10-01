//this will let our software interface with the database.
package testing.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import testing.demo.Classes.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
}
