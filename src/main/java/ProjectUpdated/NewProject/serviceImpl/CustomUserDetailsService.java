package ProjectUpdated.NewProject.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ProjectUpdated.NewProject.entity.Student;
import ProjectUpdated.NewProject.entity.Teacher;
import ProjectUpdated.NewProject.entity.User;
import ProjectUpdated.NewProject.entity.UserPrincipal;
import ProjectUpdated.NewProject.repository.StudentRepository;
import ProjectUpdated.NewProject.repository.TeacherRepository;
import ProjectUpdated.NewProject.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	       @Autowired
           private StudentRepository studentRepository;
	       @Autowired
	       private TeacherRepository teacherRepository;
	       
	       @Autowired
	       private UserRepository userRepository;
	 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		 User user = userRepository.findByUsername(username);
	        if(user !=null) {
	        	return UserPrincipal.build(user);
	        }
		 
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
