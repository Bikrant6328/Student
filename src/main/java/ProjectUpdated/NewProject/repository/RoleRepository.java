package ProjectUpdated.NewProject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ProjectUpdated.NewProject.Enums.ERole;
import ProjectUpdated.NewProject.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	 Optional<Role> findByName(ERole name);
}
