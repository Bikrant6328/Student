package ProjectUpdated.NewProject.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import ProjectUpdated.NewProject.entity.Student;

@Service
public interface StudentService{

	public Student createStudent(Student student) throws Exception;
	
	public ResponseEntity<?> loginStudent(Student student);
	
	public List<Student> getAllStudent(int pageno,int pagesize);
	
	
	
}
