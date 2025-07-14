package ProjectUpdated.NewProject.serviceImpl;

import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import ProjectUpdated.NewProject.entity.User;
import ProjectUpdated.NewProject.jwtServices.JwtService;
import ProjectUpdated.NewProject.repository.UserRepository;
import ProjectUpdated.NewProject.service.CustomUserService;
import ProjectUpdated.NewProject.service.TeacherService;

@Service
public class CustomUserServiceImplementation implements CustomUserService {
	@Autowired
    @Lazy
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserRepository userRepository;
	
	
	private static final Logger logger = LoggerFactory.getLogger(CustomUserService.class);
	
	@Override
	public ResponseEntity<?> loginUser (User user) {
	    try {
	        Authentication authentication = authenticationManager
	                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

	        if (authentication.isAuthenticated()) {
	            // Generate token using the authenticated user
	            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	            
	            // Get the user's role
	            String role = userDetails.getAuthorities().stream()
	                    .map(GrantedAuthority::getAuthority)
	                    .findFirst() // Assuming a user has only one role
	                    .orElse("ROLE_USER");
	            
	    
	            String token = jwtService.generateToken(userDetails);
	            return ResponseEntity.ok(new AuthResponse(token,role));
	        }
	    } catch (AuthenticationException e) {
	        logger.warn("Authentication failed for user: {}", user.getUsername(), e);
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: " + e.getMessage());
	    }
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
	}

	@Override
	public Optional<User> getUserById(Long id) {
		Optional<User> userdetails = userRepository.findById(id);
		return userdetails;
	}




}
