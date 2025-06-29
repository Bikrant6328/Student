package ProjectUpdated.NewProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ProjectUpdated.NewProject.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
	   List<Enrollment> findByStudentId(Long studentId);
	   Enrollment findByStudentIdAndCourseId(Long studentId, Long courseId);
}
