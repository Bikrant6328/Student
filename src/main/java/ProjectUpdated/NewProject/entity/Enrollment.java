package ProjectUpdated.NewProject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Enrollment")
public class Enrollment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollment_id;
	
	@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id")
    private Student student;
    
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

	public Long getId() {
		return enrollment_id;
	}

	public void setId(Long enrollment_id) {
		this.enrollment_id = enrollment_id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
    
    
}
