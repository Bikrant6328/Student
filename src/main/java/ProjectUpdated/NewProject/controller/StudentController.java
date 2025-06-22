package ProjectUpdated.NewProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ProjectUpdated.NewProject.entity.Student;
import ProjectUpdated.NewProject.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {
	
	@Autowired
	 private StudentService studentService;

	@GetMapping("/login")
	public String studentLoginCheck() {
		return "Welcome to Student Login Page";
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<Student> createStudent(@RequestBody Student student ) throws Exception{
	Student details = studentService.createStudent(student);
	return ResponseEntity.status(HttpStatus.CREATED).body(details);
	
    
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginStudent(@RequestBody Student student){
		String details = studentService.loginStudent(student); 
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(details);
		
	}
	
	
}
