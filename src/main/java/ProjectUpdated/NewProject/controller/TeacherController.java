package ProjectUpdated.NewProject.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ProjectUpdated.NewProject.customexception.CustomException;
import ProjectUpdated.NewProject.entity.Student;
import ProjectUpdated.NewProject.entity.Teacher;
import ProjectUpdated.NewProject.service.TeacherService;
import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/teacher")
@Slf4j
public class TeacherController {

	@Autowired
	private TeacherService teacherService;

	private static final Logger log = (Logger) LoggerFactory.getLogger(TeacherController.class);

	List<String> allTeacher = Arrays.asList("Ritesh", "Shreyansh", "Raju");

	
	@GetMapping("/getPage")
	public String teacherLogin() {
		return  "Welcome to Teacher page";
	}
	
	@PostMapping("/register")
	public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) throws Exception {
	    try {
	        Teacher details = teacherService.createTeacher(teacher);
	        return ResponseEntity.status(HttpStatus.CREATED).body(details);
	    } catch (CustomException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	    }
	}

	
	
	
	@PostMapping("/login")
	public ResponseEntity<?> loginTeacher(@RequestBody Teacher teacher) {
		ResponseEntity<?> details = teacherService.loginTeacher(teacher);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(details);

	} 
	
}
