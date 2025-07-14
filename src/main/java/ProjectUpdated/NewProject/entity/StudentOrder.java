package ProjectUpdated.NewProject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Student_Orders")
public class StudentOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	private String name;
	private String email;
	private String contact;
	private String course;
	private Integer amount;
	private String orderstatus;
	private String razorpayOrderId;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	public String getRazorpayOrderId() {
		return razorpayOrderId;
	}

	public void setRazorpayOrderId(String razorpayOrderId) {
		this.razorpayOrderId = razorpayOrderId;
	}

	public StudentOrder(Integer orderId, String name, String email, String contact, String course, Integer amount,
			String orderstatus, String razorpayOrderId) {
		super();
		this.orderId = orderId;
		this.name = name;
		this.email = email;
		this.contact = contact;
		this.course = course;
		this.amount = amount;
		this.orderstatus = orderstatus;
		this.razorpayOrderId = razorpayOrderId;
	}

	public StudentOrder() {
		super();
	}

	@Override
	public String toString() {
		return "StudentOrder [orderId=" + orderId + ", name=" + name + ", email=" + email + ", contact=" + contact
				+ ", course=" + course + ", amount=" + amount + ", orderstatus=" + orderstatus + ", razorpayOrderId="
				+ razorpayOrderId + "]";
	}

}
