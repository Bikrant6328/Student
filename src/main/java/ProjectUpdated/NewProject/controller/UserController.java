package ProjectUpdated.NewProject.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ProjectUpdated.NewProject.entity.Student;
import ProjectUpdated.NewProject.entity.User;
import ProjectUpdated.NewProject.service.CustomUserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {
	@Autowired
	private CustomUserService userService;
	

	@PostMapping("/login")
	public ResponseEntity<?> loginStudent(@RequestBody User user) {
		ResponseEntity<?> details = userService.loginUser(user);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(details);

	}
	
	@GetMapping("/getByid/{id}")
	public ResponseEntity<Optional<User>> getUserByid(@PathVariable Long id){
		Optional<User> user = userService.getUserById(id);
		return ResponseEntity.status(HttpStatus.FOUND).body(user);
	}
}
