package ProjectUpdated.NewProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ProjectUpdated.NewProject.entity.Enrollment;
import ProjectUpdated.NewProject.service.EnrollmentService;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
	
	    @Autowired
	    private EnrollmentService enrollmentService;
	    
	    
	    @PostMapping("/enroll")
	    public ResponseEntity<Enrollment> enrollStudent(@RequestParam Long studentId, @RequestParam Long courseId) {
	        Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);
	        return ResponseEntity.ok(enrollment);
	    }
	    
	    @GetMapping("/student/{studentId}")
	    public ResponseEntity<List<Enrollment>> getEnrollmentsByStudent(@PathVariable Long studentId) {
	        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudent(studentId);
	        return ResponseEntity.ok(enrollments);
	    }
	    
	    
	    @DeleteMapping("/delete")
	    public ResponseEntity<Void> unenrollStudent(@RequestParam Long studentId, @RequestParam Long courseId) {
	        enrollmentService.unenrollStudent(studentId, courseId);
	        return ResponseEntity.noContent().build();
	    }

}
