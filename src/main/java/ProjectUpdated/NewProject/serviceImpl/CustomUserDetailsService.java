package ProjectUpdated.NewProject.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ProjectUpdated.NewProject.entity.Student;
import ProjectUpdated.NewProject.entity.Teacher;
import ProjectUpdated.NewProject.entity.UserPrincipal;
import ProjectUpdated.NewProject.repository.StudentRepository;
import ProjectUpdated.NewProject.repository.TeacherRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	       @Autowired
           private StudentRepository studentRepository;
	       @Autowired
	       private TeacherRepository teacherRepository;
	 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 
        // Check STUDENT repository first
        Student student = studentRepository.findByUsername(username);
        if (student != null) {
            return UserPrincipal.build(student);  
        }
        // If not STUDENT, check TEACHER repository
        Teacher teacher = teacherRepository.findByUsername(username);
        if (teacher != null) {  
            return UserPrincipal.build(teacher);
        }
        // Neither STUDENT nor TEACHER found → throw error
        throw new UsernameNotFoundException("User not found: " + username);
	}

}
