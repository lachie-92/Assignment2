package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class App {
	
	private ArrayList<Ticket> tickets;
	private ArrayList<User> users;
	private User current_user;
	final public Scanner scanner;

	

	public App() {
		this.tickets = new ArrayList<Ticket>();
		this.users = new ArrayList<User>();
		this.current_user = null;
		this.scanner = new Scanner(System.in);
		
		init_users();
		
	}
	
	public List<User> getUsers() {
		return this.users;
	}
	
	public List<Ticket> getTickets() {
		return this.tickets;
	}
	
	public User getCurrentUser() {
		return this.current_user;
	}
	
	public void setCurrentUser(User user) {
		this.current_user = user;	
	}
	
	// remove current user from session.
	public void logout() {
		this.current_user = null;
	}
	
	// Store a new ticket against the application.
	public void storeNewTicket(Ticket ticket) {
		this.tickets.add(ticket);
	}
	
	// Store a new User against the application
	public void storeNewUser(User user) {
		this.users.add(user);
	}

	// For Testing - Itterates over tickets Arraylist and prints each ticket details
	public void printAllTickets() {
		System.out.println("Testing all system tickets");
        System.out.println("----------------------");
		for(int i = 0; i < this.tickets.size(); i++) {
			System.out.println("["+i+"] - "+this.tickets.get(i).toString());
		}
	}
	
	// For Testing - Itterates over users Arraylist and prints each user details
	public void printAllUsers() {
		System.out.println("Testing all system tickets");
        System.out.println("----------------------");
		for(int i = 0; i < this.users.size(); i++) {
			System.out.println("["+i+"] - "+this.users.get(i).toString());
		}
	}
	
	
	/* Initialise Existing System Users */
	private void init_users() {
		
		String default_password = "Aaaaaaaaaaa01111111111";
		
		// Initialise Level 1 Service Technicians
		this.users.add(new User(this.users.size(),"Harry Styles","harry@cinco.com.au","0400 001 002",default_password,"Technician",1));
		this.users.add(new User(this.users.size(),"Niall Horan","niall@cinco.com.au","0400 002 002",default_password,"Technician",1));
		this.users.add(new User(this.users.size(),"Liam Payne","liam@cinco.com.au","0400 003 003",default_password,"Technician",1));
		
		// Initialise Level 2 Service Technicians
		this.users.add(new User(this.users.size(),"Louis Tomlinson","louis@cinco.com.au","0400 004 004",default_password,"Technician",2));
		this.users.add(new User(this.users.size(),"Zayn Malik","zayn@cinco.com.au","0400 005 005",default_password,"Technician",2));
		
		// Initialise a staff member
		this.users.add(new User(this.users.size(),"Chris Warrens","chris@cinco.com.au","0400 111 111",default_password,"Staff",0));
		
	}

	

	
}
