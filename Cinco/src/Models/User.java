package Models;

public class User {
	
	private int id;
	private String name;
	private String email;
	private String phone;
	private String password;
	private String role;
	private int service_desk_level;
	
	// User Constructor
	public User(String name, String email, String phone, String password,String role,int service_desk_level) {
		this.id = 1; // PLACEHOLDER
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.role = role;
		this.service_desk_level = service_desk_level;
	}
	
	// Get user position in array
	public int getId() {
		// Just a placeholder for now.
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	// To String override for testing
	@Override
	public String toString() {
		
		return String.format("role - %s, service_desk_level - %d, name - %s, email - %s, phone - %s, password - %s", 
				this.role, this.service_desk_level, this.name, this.email, this.phone, this.password);
	}
	
}
