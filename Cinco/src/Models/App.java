package Models;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

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

	// start background service to automatically archive tickets older than 24h
	public void start_BGS() {

		Timer timer = new Timer();

		timer.schedule(new TimerTask() {

			@Override
			public void run() {

				// test if background service working
				//System.out.println("Checking for closed tickets...");

				// filters out tickets that are closed
				List<Ticket> closedTickets = tickets.stream().filter(s -> s.getStatus() == Status.Closed)
						.collect(Collectors.toList());

				for (Ticket t : closedTickets) {

					// compares current time to tickets creation time, if older than 24 hours
					if (t.getCreatedAt().until(LocalDateTime.now(), ChronoUnit.DAYS) >= 1) {

						t.archiveTicket();
					}
				}

			}
		}, 0, 60 * 1000);

	}

	public void assignTicketToTechnician(Ticket ticket) {
		List<User> technicians = users.stream().filter(x -> x.getRole() == Role.Technician)
				.collect(Collectors.toList());

		// Find technicians that match ticket's service level
		if (ticket.getServiceDesk() == 1) {
			technicians = technicians.stream().filter(x -> x.getServiceDeskLevel() == 1).collect(Collectors.toList());
		} else {
			technicians = technicians.stream().filter(x -> x.getServiceDeskLevel() == 2).collect(Collectors.toList());
		}

		// Find technician with the least num of tickets
		User technician = Collections.min(technicians, Comparator.comparing(s -> s.getTickets().size()));
		ticket.setTechnicianId(technician.getId());
		technician.addTicket(ticket);

		System.out.println("Ticket assigned to " + technician.getName());
	}

	// For Testing - Itterates over tickets Arraylist and prints each ticket details
	public void printAllTickets() {
		System.out.println("Testing all system tickets");
		System.out.println("----------------------");
		for (int i = 0; i < this.tickets.size(); i++) {
			System.out.println("[" + i + "] - " + this.tickets.get(i).toString());
		}
	}

	// Print all tickets for the currently logged in Staff Member
	public void printStaffTickets(int userID) {
		System.out.println("All Your Open Tickets");
		System.out.println("----------------------");
		if (this.tickets.size() != 0) {
			for (int i = 0; i < this.tickets.size(); i++) {
				if (this.tickets.get(i).getUserID() == this.current_user.getId()) {
					System.out.println("[" + i + "] - " + this.tickets.get(i).toString());
				}
			}
		} else {
			System.out.println("You have no open tickets");
		}

	}

	// For Testing - Itterates over users Arraylist and prints each user details
	public void printAllUsers() {
		System.out.println("Testing all system tickets");
		System.out.println("----------------------");
		for (int i = 0; i < this.users.size(); i++) {
			System.out.println("[" + i + "] - " + this.users.get(i).toString());
		}
	}

	/* Initialise Existing System Users */
	private void init_users() {

		String default_password = "Aaaaaaaaaaa01111111111";

		// Initialise Level 1 Service Technicians
		this.users.add(new User(this.users.size(), "Harry Styles", "harry@cinco.com.au", "0400 001 002",
				default_password, Role.Technician, 1));
		this.users.add(new User(this.users.size(), "Niall Horan", "niall@cinco.com.au", "0400 002 002",
				default_password, Role.Technician, 1));
		this.users.add(new User(this.users.size(), "Liam Payne", "liam@cinco.com.au", "0400 003 003", default_password,
				Role.Technician, 1));

		// Initialise Level 2 Service Technicians
		this.users.add(new User(this.users.size(), "Louis Tomlinson", "louis@cinco.com.au", "0400 004 004",
				default_password, Role.Technician, 2));
		this.users.add(new User(this.users.size(), "Zayn Malik", "zayn@cinco.com.au", "0400 005 005", default_password,
				Role.Technician, 2));

		// Initialise a staff member
		this.users.add(new User(this.users.size(), "Chris Warrens", "chris@cinco.com.au", "0400 111 111",
				default_password, Role.Staff, 0));
	}

}
