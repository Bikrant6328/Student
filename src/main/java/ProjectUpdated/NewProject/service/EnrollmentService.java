package ProjectUpdated.NewProject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ProjectUpdated.NewProject.entity.Enrollment;

@Service
public interface EnrollmentService {
	Enrollment enrollStudent(Long studentId, Long courseId);
	
	public void unenrollStudent(Long studentId, Long courseId);
	
	List<Enrollment> getEnrollmentsByStudent(Long studentId);
}
