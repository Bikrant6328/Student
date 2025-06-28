package ProjectUpdated.NewProject.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	public Student(long id, @NotBlank @Size(max = 50) String username, @NotBlank @Size(max = 120) String password,
			@NotBlank @Size(max = 50) String email, Set<Role> roles, String contact, String stream) {
		
		super(id, username, password, email, roles);
		this.contact = contact;
		this.stream = stream;
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

}