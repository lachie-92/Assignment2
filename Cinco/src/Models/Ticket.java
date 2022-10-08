package Models;

import java.time.LocalDateTime;

import Models.App;
import Models.Status;
import Models.Severity;

public class Ticket {
	
	private int user_id;
	private int technician_id;
	private Status status;
	private Severity severity;
	private String description;
	private LocalDateTime created_at;
	private Boolean archived;
	
	public Ticket(int user_id, Severity severity, 
				  String description) {
		
		this.user_id = user_id;
		this.status = Status.Open;
		this.severity = severity;
		this.description = description;
		this.created_at = LocalDateTime.now();
		this.archived = false;
		assignTicketToTechnician();
	}
	
	private void assignTicketToTechnician() {
		this.technician_id = 1;
	}
	
	/* Create new ticket in system */
	public static void create(App App) {
		
		System.out.println("Create New Ticket");
        System.out.println("----------------------");
        
        // Request severity from user
		System.out.println("Please select a severity of the issue from (1) Low, (2) Medium, (3) High by entering the corresponding number:");
		Severity severity = Severity.values()[App.scanner.nextInt() - 1];
		// Finish buffering the next int.
		App.scanner.nextLine();
		// Request description from user
		System.out.println("Please enter a description of the issue");
		String description = App.scanner.nextLine();
		
		// Create new Ticket
		Ticket ticket = new Ticket(1, severity, description);
		
		// Store ticket in Global Tickets List
		App.storeNewTicket(ticket);
		
		// List all tickets for testing
		App.printAllTickets();
		
		System.out.println("Your ticket has been entered.");
		
		return;
	}
	
	// To String override for testing
	@Override
	public String toString() {
		return String.format("Status - %s, Severity - %s, Description - %s, Created - %s", 
				             this.status, this.severity, this.description, this.created_at);
	}

	
	

}
