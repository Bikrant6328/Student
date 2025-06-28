package ProjectUpdated.NewProject.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "teachers")
public class Teacher extends User {

	private String department;
	private String subject;

	public Teacher(long id, @NotBlank @Size(max = 50) String username, @NotBlank @Size(max = 120) String password,
			@NotBlank @Size(max = 50) String email, Set<Role> roles, String department, String subject) {

		super(id, username, password, email, roles);
		this.department = department;
		this.subject = subject;
	}

	public Teacher() {
		super();

	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

//	public Teacher(String username, @NotBlank @Size(max = 120) String password, @NotBlank @Size(max = 50) String email,
//			String department, String subject) {
//
//		this.department = department;
//		this.subject = subject;
//	}

}
