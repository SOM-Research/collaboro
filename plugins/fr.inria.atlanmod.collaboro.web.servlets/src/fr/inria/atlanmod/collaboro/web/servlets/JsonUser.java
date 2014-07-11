package fr.inria.atlanmod.collaboro.web.servlets;

/**
 * POJO class to represent user object in the login servlet
 * 
 */
public class JsonUser {
	private String email;
	private String password;
	private String dsl;
	
	public String getDsl() {
		return dsl;
	}

	public void setDsl(String dsl) {
		this.dsl = dsl;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
