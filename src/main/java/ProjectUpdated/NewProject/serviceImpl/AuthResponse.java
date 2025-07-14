package ProjectUpdated.NewProject.serviceImpl;

import java.util.Optional;

import ProjectUpdated.NewProject.entity.User;

public class AuthResponse {
	private String token;
	private String role;
	


	public AuthResponse(String token, String role) {
		super();
		this.token = token;
		this.role = role;
	
	}

	public AuthResponse() {
		super();
	}

	public String getToken() {
		return token;
	}

	public String getRole() {
		return role;
	}
	
	
	
	
	public AuthResponse(String token) {

		this.token = token;
		
	}

}
