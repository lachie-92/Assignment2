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
	
	public void storeNewTicket(Ticket ticket) {
		this.tickets.add(ticket);
	}
	
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
		
		// Initialise Level 1 Service Technicians
		this.users.add(new User("Harry Styles","harry@cinco.com.au","0400 001 002","password","Technician",1));
		this.users.add(new User("Niall Horan","niall@cinco.com.au","0400 002 002","password","Technician",1));
		this.users.add(new User("Liam Payne","liam@cinco.com.au","0400 003 003","password","Technician",1));
		
		// Initialise Level 2 Service Technicians
		this.users.add(new User("Louis Tomlinson","louis@cinco.com.au","0400 004 004","password","Technician",2));
		this.users.add(new User("Zayn Malik","zayn@cinco.com.au","0400 005 005","password","Technician",2));
		
	}
}
