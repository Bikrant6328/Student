package ProjectUpdated.NewProject.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProjectUpdated.NewProject.entity.Course;
import ProjectUpdated.NewProject.entity.Enrollment;
import ProjectUpdated.NewProject.entity.Student;
import ProjectUpdated.NewProject.repository.CourseRepository;
import ProjectUpdated.NewProject.repository.EnrollmentRepository;
import ProjectUpdated.NewProject.repository.StudentRepository;
import ProjectUpdated.NewProject.service.EnrollmentService;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private EnrollmentRepository enrollmentRepository;

	@Override
	public Enrollment enrollStudent(Long studentId, Long courseId) {
		Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        return enrollmentRepository.save(enrollment);
	}

	@Override
	public void unenrollStudent(Long studentId, Long courseId) {
		Enrollment enrollment = enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId);
        if (enrollment != null) {
            enrollmentRepository.delete(enrollment);
        }

	}

	@Override
	public List<Enrollment> getEnrollmentsByStudent(Long studentId) {
		 return enrollmentRepository.findByStudentId(studentId);
	}

}
