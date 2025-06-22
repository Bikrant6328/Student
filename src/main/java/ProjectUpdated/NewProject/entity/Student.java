package ProjectUpdated.NewProject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Student_Table")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer student_id;

	@Column(name = "Student_Name")
	@NotNull
	private String username;

	@Column(name = "Student_Password")
	@NotNull
	private String password;

	@Column(name = "Student_email")
	@Email(message = "email Can not be null")
	@NotNull
	private String email;

	@Column(name = "Student_contact")
	private String contact; // Change from Integer to String

	@Column(name = "Student_Stream")
	private String stream;

	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() { // Change return type to String
		return contact;
	}

	public void setContact(String contact) { // Change parameter type to String
		this.contact = contact;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public Student(Integer student_id, @NotNull String username, @NotNull String password,
			@Email(message = "email Can not be null") @NotNull String email, String contact, String stream) {
		super();
		this.student_id = student_id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.contact = contact;
		this.stream = stream;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Student [student_id=" + student_id + ", username=" + username + ", password=" + password + ", email="
				+ email + ", contact=" + contact + ", stream=" + stream + "]";
	}



	

	
}