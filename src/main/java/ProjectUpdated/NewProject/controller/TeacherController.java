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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ProjectUpdated.NewProject.jwtServices.JwtService;
import ch.qos.logback.classic.Logger;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/access")
@Slf4j
public class TeacherController {
	@Autowired
	private JwtService jwtService;

	private static final Logger log = (Logger) LoggerFactory.getLogger(TeacherController.class);

	List<String> allTeacher = Arrays.asList("Ritesh", "Shreyansh", "Raju");

	@GetMapping("/getAll")
	public ResponseEntity<?> allTeachers(@RequestHeader("Authorization") String authHeader) {
		try {
			// Check if the token is present and valid
			if (authHeader != null && authHeader.startsWith("Bearer ")) {
				String token = authHeader.substring(7); // Remove "Bearer "
				if (jwtService.isTokenExpired(token)) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token expired. Please log in again.");
				}
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body("Authorization header is missing or invalid.");
			}
			log.info("Fetching all teachers");
			return ResponseEntity.ok(allTeacher); // Return the list of teachers
		} catch (Exception e) {
			log.error("Error fetching teachers", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing request");
		}

	}
}
