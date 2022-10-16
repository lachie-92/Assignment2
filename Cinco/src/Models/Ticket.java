package Models;

import java.time.LocalDateTime;

import Models.App;
import Models.Status;
import Models.Severity;

public class Ticket {

	private int id;
	private int user_id;
	private int technician_id;
	private Status status;
	private Severity severity;
	private int serviceDesk;
	private String description;
	private LocalDateTime created_at;
	private LocalDateTime closed_at;
	private Boolean archived;

	public Ticket(int ticket_id, int user_id, Severity severity, 
			String description) {
		this.id = ticket_id;
		this.user_id = user_id;
		this.status = Status.Open;
		this.severity = severity;
		this.description = description;
		this.created_at = LocalDateTime.now();
		this.archived = false;
		assignServiceDesk();
	}
	
	public int getUserID() {
		// TODO Auto-generated method stub
		return this.user_id;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public int getTechnicianId() {
		return this.technician_id;
	}
	
	public Severity getSeverity() {
		return this.severity;
	}
	
	public int getServiceDesk() {
		return this.serviceDesk;
	}
	
	public void setTechnicianId(int id) {
		this.technician_id = id;
	}

	private void assignServiceDesk() {
		if (this.severity.equals(Severity.High)) {
			this.serviceDesk = 2;
		} else {		
			this.serviceDesk = 1;
		}
	}
	
	public void setSeverity(App App) {
		System.out.print("\r\n");
		System.out.println("----------------------");
		System.out.println("Edit Ticket Severity");
		System.out.println("----------------------");

		// Request severity from user
		System.out.println("Please select a severity of the issue from (1) Low, (2) Medium, (3) High by entering the corresponding number:");
		Severity severity = Severity.values()[App.scanner.nextInt() - 1];
		System.out.println(severity);
		// Finish buffering the next int.
		App.scanner.nextLine();
		
		this.severity = severity;
	}
	
	public void setStatus(App App) {
		System.out.print("\r\n");
		System.out.println("----------------------");
		System.out.println("Edit Ticket Status");
		System.out.println("----------------------");

		// Request severity from user
		System.out.println("Please select a status from (1) Resolved, (2) Unresolved by entering the corresponding number:");
		Status status = Status.values()[App.scanner.nextInt() + 1];
		// Finish buffering the next int.
		App.scanner.nextLine();
		
		this.status = status;
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
		Ticket ticket = new Ticket( App.getTickets().size() ,App.getCurrentUser().getId(), severity, description);

		// Store ticket in Global Tickets List
		App.storeNewTicket(ticket);
		
		// Assign Ticket to technician with least tickets
		App.assignTicketToTechnician(ticket);

		System.out.println("Your ticket has been entered.");

		return;
	}


	// To String override for testing
	@Override
	public String toString() {
		return String.format("Status - %s, Severity - %s, Service Desk - %s, Description - %s, Created - %s", 
				this.status, this.severity, this.serviceDesk, this.description, this.created_at);
	}
	
	public void closeTicket() {
		
		this.status = Status.Closed;
		this.closed_at = LocalDateTime.now();
	}

	public LocalDateTime getClosedTime() {
		
		return this.closed_at;
	}

	public void archiveTicket() {
		this.archived = true;
		
	}

	public Status getStatus() {
		return this.status;
	}




}
