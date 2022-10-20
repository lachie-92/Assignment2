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

import Auth.Encryption;
import Seeders.TicketSeeder;
import Seeders.UserSeeder;

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

		UserSeeder.seed_users(this);
		TicketSeeder.seed_tickets(this);

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
				List<Ticket> closedTickets = tickets.stream().filter(s -> s.getStatus() != Status.Open)
						.collect(Collectors.toList());

				for (Ticket t : closedTickets) {

					// compares current time to tickets creation time, if older than 24 hours
					if (t.getClosedTime().until(LocalDateTime.now(), ChronoUnit.DAYS) >= 1) {

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

		return;
	}

	


}
