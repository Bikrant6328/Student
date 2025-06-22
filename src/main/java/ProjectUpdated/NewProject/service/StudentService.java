package ProjectUpdated.NewProject.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import ProjectUpdated.NewProject.entity.Student;

@Service
public interface StudentService extends UserDetailsService{

	public Student createStudent(Student student) throws Exception;
	
	public String loginStudent(Student student);
	
	public ResponseEntity<List<Student>> getAllStudent();
	
	
	
}
