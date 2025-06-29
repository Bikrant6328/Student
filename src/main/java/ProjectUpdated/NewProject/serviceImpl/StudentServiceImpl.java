package ProjectUpdated.NewProject.serviceImpl;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ProjectUpdated.NewProject.Enums.ERole;
import ProjectUpdated.NewProject.customexception.CustomException;
import ProjectUpdated.NewProject.entity.Role;
import ProjectUpdated.NewProject.entity.Student;
import ProjectUpdated.NewProject.entity.UserPrincipal;
import ProjectUpdated.NewProject.jwtServices.JwtService;
import ProjectUpdated.NewProject.repository.StudentRepository;
import ProjectUpdated.NewProject.service.StudentService;
import ProjectUpdated.NewProject.service.TeacherService;
import jakarta.transaction.Transactional;
import ProjectUpdated.NewProject.repository.RoleRepository;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private RoleRepository roleRepository; 

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

   @Transactional
    @Override
    public Student createStudent(Student student) throws Exception {
        if (student == null) {
            throw new CustomException("405", "Student details should not be Null");
        }

        // Create a new student object
        Student details = new Student();
        details.setUsername(student.getUsername());
        details.setPassword(encoder.encode(student.getPassword())); // Encrypt password
        details.setEmail(student.getEmail());
        details.setContact(student.getContact());
        details.setStream(student.getStream());
        details.setStudentname(student.getStudentname());

        // Assign roles to the student
        Role studentRole = roleRepository.findByName(ERole.ROLE_STUDENT)
                .orElseThrow(() -> new CustomException("404", "Role not found"));
        details.getRoles().add(studentRole);

        // Save the student
        return studentRepo.save(details);
    }

   private static final Logger logger = LoggerFactory.getLogger(TeacherService.class);
   
    @Override
    public ResponseEntity<?> loginStudent(Student student) {
    	try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(student.getUsername(), student.getPassword()));

            if (authentication.isAuthenticated()) {
                // Generate token using the authenticated user
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                
                // Check if the user has the required role
                Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
                boolean hasRole = authorities.stream()
                        .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_STUDENT"));

                if (hasRole) {
                    String token = jwtService.generateToken(userDetails);
                    return ResponseEntity.ok(new AuthResponse(token));
                } else {
                    logger.warn("User  {} does not have the required role", student.getUsername());
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: insufficient permissions");
                }
            }
        } catch (AuthenticationException e) {
            logger.warn("Authentication failed for user: {}", student.getUsername(), e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
    }

    @Override
    public List<Student> getAllStudent(int pageno, int pageSize) {
        Pageable page = PageRequest.of(pageno, pageSize);
        Page<Student> alldetails = studentRepo.findAll(page);
        return alldetails.getContent();
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println("Attempting to load user by username: " + username);
//
//        Student userdetails = studentRepo.findByUsername(username);
//
//        if (userdetails == null) {
//            System.out.println("User  not found: " + username);
//            throw new UsernameNotFoundException("User  not found: " + username);
//        }
//
//        System.out.println("User  found: " + userdetails.getUsername());
//        return UserPrincipal.build(userdetails);
//    }
}
