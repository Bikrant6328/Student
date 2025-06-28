package ProjectUpdated.NewProject.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import ProjectUpdated.NewProject.entity.Teacher;

@Service
public interface TeacherService {

	public Teacher createTeacher(Teacher teacher) throws Exception;

	public ResponseEntity<?> loginTeacher(Teacher teacher);

	public List<Teacher> getAllTeachers(int pageno, int pagesize);


}
