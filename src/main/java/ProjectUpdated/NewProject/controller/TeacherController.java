package ProjectUpdated.NewProject.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/access")
@Slf4j
public class TeacherController {
	private static final Logger log = (Logger) LoggerFactory.getLogger(TeacherController.class);
	
	List<String> allTeacher = Arrays.asList("Ritesh", "Shreyansh", "Raju");
	
	@GetMapping("/getAll")
	public List<String> allTeachers() {
		log.info("Fetching all teachers");
		List<String> allData = allTeacher.stream().map(t -> t).collect(Collectors.toList());
		return allData;
	}
	
}

