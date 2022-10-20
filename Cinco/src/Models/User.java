package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Auth.Encryption;

public class User {
	
	private int id;
	private String name;
	private String email;
	private String phone;
	private String password;
	private Role role;
	private int service_desk_level;
	private ArrayList<Ticket> tickets = new ArrayList<Ticket>();
	
	// User Constructor
	public User(int id, String name, String email, String phone, String password, Role role,int service_desk_level) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = Encryption.encryptPassword(password);
		this.role = role;
		this.service_desk_level = service_desk_level;
	}
	
	// Get ID of user
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Role getRole() {
		return this.role;
	}
	
	public int getServiceDeskLevel() {
		return this.service_desk_level;
	}
	
	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		// Encrypt and set the User password
		this.password = Encryption.encryptPassword(password);
	}
	
	public ArrayList<Ticket> getTickets() {
		return this.tickets;
	}
	
	public void addTicket(Ticket ticket) {
		this.tickets.add(ticket);
	}
	
	public void printOpenTickets(App App) {
		// Get all Open tickets for this user
		List<Ticket> tickets = App.getTickets()
				  .stream()
				  .filter(ticket -> ticket.getUserID() == this.id)
				  .filter(ticket -> ticket.getStatus() == Status.Open)
				  .collect(Collectors.toList());
		
		// If there are no tickets return error 
		if(tickets.size() == 0) {
			Utility.print_error("There are currently no existing tickets in the system.");
			return;
		}
	 
		System.out.println();
		System.out.println("Open Tickets : " + this.name);
		Utility.print_hr(150);
		System.out.println(String.format("| %-10s | %-25s | %-10s | %-25s | %-60s ", 
							"Ticket ID","Submit Date","Severity","Assigned To", "Description"));
		Utility.print_hr(150);
		
		// Iterate over each ticket and print details
		for(Ticket t : tickets) {
			User technician = App.getUsers().get(t.getTechnicianId());
			t.printStaffTicket(technician.getName());

		}
		
		Utility.print_hr(150);
		
		return;
	}


	
}
