//this will let our software interface with the database.
package testing.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import testing.demo.Classes.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
