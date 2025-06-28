package ProjectUpdated.NewProject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ProjectUpdated.NewProject.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	 Optional<User> findByUsername(String username);
 
}
