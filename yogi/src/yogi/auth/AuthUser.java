package yogi.auth;

import java.io.Serializable;

public class AuthUser implements Serializable{
	
	private static final long serialVersionUID = 8926354023614537578L;
	
	private String firstName;
	private String lastName;
	private String userId;
	private String role;
	private String email;
	private String companyCode;
	private String location;
	private String sessionId;
	private String transactionId;
	

	public AuthUser(String firstName, String lastName, String userId,
			String role, String email, String companyCode, String location,
			String sessionId, String transactionId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userId = userId;
		this.role = role;
		this.email = email;
		this.companyCode = companyCode;
		this.location = location;
		this.sessionId = sessionId;
		this.transactionId = transactionId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUserId() {
		return userId;
	}

	public String getRole() {
		return role;
	}

	public String getEmail() {
		return email;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public String getLocation() {
		return location;
	}

	public String getSessionId() {
		return sessionId;
	}

	public String getTransactionId() {
		return transactionId;
	}

}