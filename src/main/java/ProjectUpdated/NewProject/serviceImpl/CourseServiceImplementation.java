package ProjectUpdated.NewProject.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProjectUpdated.NewProject.entity.Course;
import ProjectUpdated.NewProject.repository.CourseRepository;
import ProjectUpdated.NewProject.service.CourseService;
import jakarta.transaction.Transactional;

@Service
public class CourseServiceImplementation implements CourseService {
	
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	@Override
	public Course getCourseById(Long id) {
		 return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
	}

	@Override
	@Transactional
	public Course createCourse(Course course) {
		 return courseRepository.save(course);
	}

	@Override
	public Course updateCourse(Long id, Course courseDetails) {
		  Course course = getCourseById(id);
	        course.setTitle(courseDetails.getTitle());
	        course.setInstructor(courseDetails.getInstructor());
	        course.setCategory(courseDetails.getCategory());
	        course.setDescription(courseDetails.getDescription());
	        course.setDuration(courseDetails.getDuration());
	        course.setVersion(courseDetails.getVersion());
	        return courseRepository.save(course);
	}

	@Override
	public void deleteCourse(Long id) {
		Course course = getCourseById(id);
        courseRepository.delete(course);

	}

}
