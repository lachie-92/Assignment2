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
	public User(int id, String name, String email, String phone, String password,String role,int service_desk_level) {
		this.id = id;
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
	
	public String getRole() {
		return this.role;
	}
	
	public String getEmail() {
		// TODO Auto-generated method stub
		return this.email;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	
	// To String override for testing
	@Override
	public String toString() {
		
		return String.format("ID %d- role - %s, service_desk_level - %d, name - %s, email - %s, phone - %s, password - %s", 
				this.id,this.role, this.service_desk_level, this.name, this.email, this.phone, this.password);
	}


	
}
