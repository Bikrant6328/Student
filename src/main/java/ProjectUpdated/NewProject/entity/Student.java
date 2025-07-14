package ProjectUpdated.NewProject.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Student_Table")
public class Student extends User {

	
	private String contact; // Change from Integer to String


	private String stream;
//	private String studentname;
	
	@JsonIgnore
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Enrollment> enrollments = new HashSet<>();

	public Student(long id, @NotBlank @Size(max = 50) String username, @NotBlank @Size(max = 120) String password,
			@NotBlank @Size(max = 50) String email, Set<Role> roles, String contact, String stream, String studentname,
			Set<Enrollment> enrollments) {
		super(id, username, password, email, roles);
		this.contact = contact;
		this.stream = stream;
		//this.studentname = studentname;
		this.enrollments = enrollments;
	}

	public Student() {
		super();
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

//	public String getStudentname() {
//		return studentname;
//	}

//	public void setStudentname(String studentname) {
//		this.studentname = studentname;
//	}

	public Set<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(Set<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}



	
	

}