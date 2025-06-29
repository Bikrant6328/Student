package ProjectUpdated.NewProject.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name="Course")
public class Course {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String title;
	    private String instructor;
	    private String category;
	    private String description;
	    private int duration;
	    
	    @JsonIgnore
	    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	    private Set<Enrollment> enrollments = new HashSet<>();
	    
	    @Version
	    private Long version; 

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getInstructor() {
			return instructor;
		}

		public void setInstructor(String instructor) {
			this.instructor = instructor;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public int getDuration() {
			return duration;
		}

		public void setDuration(int duration) {
			this.duration = duration;
		}

		public Set<Enrollment> getEnrollments() {
			return enrollments;
		}

		public void setEnrollments(Set<Enrollment> enrollments) {
			this.enrollments = enrollments;
		}

		public Course(Long id, String title, String instructor, String category, String description, int duration,
				Set<Enrollment> enrollments) {
			super();
			this.id = id;
			this.title = title;
			this.instructor = instructor;
			this.category = category;
			this.description = description;
			this.duration = duration;
			this.enrollments = enrollments;
		}

		public Course() {
			super();
		}
		
		
	    
	    
		
	    
}
