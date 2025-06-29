package ProjectUpdated.NewProject.serviceImpl;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import ProjectUpdated.NewProject.entity.Teacher;
import ProjectUpdated.NewProject.entity.UserPrincipal;
import ProjectUpdated.NewProject.jwtServices.JwtService;
import ProjectUpdated.NewProject.repository.RoleRepository;
import ProjectUpdated.NewProject.repository.TeacherRepository;
import ProjectUpdated.NewProject.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println("Attempting to load user by username: " + username);
//
//        Teacher userDetails = teacherRepository.findByUsername(username);
//
//        if (userDetails == null) {
//            System.out.println("User  not found: " + username);
//            throw new UsernameNotFoundException("User  not found: " + username);
//        }
//
//        System.out.println("User  found: " + userDetails.getUsername());
//        return UserPrincipal.build(userDetails);
//    }

    @Override
    public Teacher createTeacher(Teacher teacher) throws Exception {
        if (teacher == null) {
            throw new CustomException("405", "Teacher details should not be Null");
        }

        Teacher details = new Teacher();
        details.setUsername(teacher.getUsername());
        details.setPassword(encoder.encode(teacher.getPassword())); // Encrypt password
        details.setEmail(teacher.getEmail());
        details.setDepartment(teacher.getDepartment()); // Assuming you have a department field
        details.setSubject(teacher.getSubject());
        details.setTeachername(teacher.getTeachername());
        
        // Assign roles to the teacher
        Role teacherRole = roleRepository.findByName(ERole.ROLE_TEACHER)
                .orElseThrow(() -> new CustomException("404", "Role not found"));
        
        details.getRoles().add(teacherRole);

        // Save the teacher
        return teacherRepository.save(details);
    }


    private static final Logger logger = LoggerFactory.getLogger(TeacherService.class);

    @Override
    public ResponseEntity<?> loginTeacher(Teacher teacher) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(teacher.getUsername(), teacher.getPassword()));

            if (authentication.isAuthenticated()) {
                // Generate token using the authenticated user
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                
                // Check if the user has the required role
                Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
                boolean hasRole = authorities.stream()
                        .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_TEACHER"));

                if (hasRole) {
                    String token = jwtService.generateToken(userDetails);
                    return ResponseEntity.ok(new AuthResponse(token));
                } else {
                    logger.warn("User  {} does not have the required role", teacher.getUsername());
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: insufficient permissions");
                }
            }
        } catch (AuthenticationException e) {
            logger.warn("Authentication failed for user: {}", teacher.getUsername(), e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
    }


    @Override
    public List<Teacher> getAllTeachers(int pageno, int pageSize) {
        Pageable page = PageRequest.of(pageno, pageSize);
        Page<Teacher> allDetails = teacherRepository.findAll(page);
        return allDetails.getContent();
    }
}
