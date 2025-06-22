package ProjectUpdated.NewProject.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.stereotype.Service;
import ProjectUpdated.NewProject.customexception.CustomException;
import ProjectUpdated.NewProject.entity.Student;
import ProjectUpdated.NewProject.entity.UserPrincipal;
import ProjectUpdated.NewProject.jwtServices.JwtService;
import ProjectUpdated.NewProject.repository.StudentRepository;
import ProjectUpdated.NewProject.service.StudentService;
import org.springframework.security.authentication.AuthenticationManager;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	@Lazy
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	private StudentRepository studentRepo;

	@Override
	public Student createStudent(Student student) throws Exception {

		if (student == null) {
			throw new CustomException("405", "Student details should not be Null");
		}

		;

		Student details = new Student();
		details.setStudent_id(student.getStudent_id());
		details.setUsername(student.getUsername());
		details.setPassword(encoder.encode(student.getPassword()));
		details.setEmail(student.getEmail());
		details.setContact(student.getContact());
		details.setStream(student.getStream());

		Student saveAll = studentRepo.save(details);
		return saveAll;

	}

	@Override
	public String loginStudent(Student student) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(student.getUsername(), student.getPassword()));

		if (authentication.isAuthenticated()) {
			return jwtService.generateToken(student.getUsername());
		}
//		Authentication authentication = authManager
//				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//		if (authentication.isAuthenticated()) {
//			return jwtService.generateToken(user.getUsername());
//			
//		}
//		return "Fail";

		return "fail";
	}

	@Override
	public ResponseEntity<List<Student>> getAllStudent() {

		List<Student> allStudents = studentRepo.findAll();
		return (ResponseEntity<List<Student>>) allStudents.stream().map(student -> student)
				.collect(Collectors.toList());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Attempting to load user by username: " + username);

		Student userdetails = studentRepo.findByUsername(username);

		if (userdetails == null) {
			System.out.println("User  Not found: " + username);
			throw new UsernameNotFoundException("User  not found: " + username);
		}

		System.out.println("User  found: " + userdetails.getUsername());
		return new UserPrincipal(userdetails);
	}

}
