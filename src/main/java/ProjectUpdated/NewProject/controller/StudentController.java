package ProjectUpdated.NewProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JacksonInject.Value;

import ProjectUpdated.NewProject.entity.Student;
import ProjectUpdated.NewProject.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/getlogin")
	public String studentLoginCheck() {
		return "Welcome to Student Login Page";
	}

	@PostMapping("/register")
	public ResponseEntity<Student> createStudent(@RequestBody Student student) throws Exception {
		Student details = studentService.createStudent(student);
		return ResponseEntity.status(HttpStatus.CREATED).body(details);

	}
	
	
	
	@GetMapping("/getAllStudents")
	public ResponseEntity<List<Student>> getAllStudents (@RequestParam(value="pageno",defaultValue="0",required=false) int pageno,
			                                             @RequestParam(value="pagesize",defaultValue ="2",required=false) int pagesize){
		
		List<Student> allStudents = studentService.getAllStudent(pageno, pagesize);
		
		 return ResponseEntity.status(HttpStatus.ACCEPTED).body(allStudents);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginStudent(@RequestBody Student student) {
		ResponseEntity<?> details = studentService.loginStudent(student);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(details);

	}

}
