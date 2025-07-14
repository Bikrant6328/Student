package ProjectUpdated.NewProject.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ProjectUpdated.NewProject.entity.Course;
import ProjectUpdated.NewProject.entity.Student;
import ProjectUpdated.NewProject.entity.User;

@Service
public interface CustomUserService {
	public ResponseEntity<?> loginUser(User user);
	
	Optional<User> getUserById(Long id);
}
