package ProjectUpdated.NewProject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ProjectUpdated.NewProject.entity.Course;

@Service
public interface CourseService {

	List<Course> getAllCourses();
	
	Course getCourseById(Long id);
	
	Course createCourse(Course course);
	
	Course updateCourse(Long id, Course courseDetails);
	
	public void deleteCourse(Long id);
	
}
