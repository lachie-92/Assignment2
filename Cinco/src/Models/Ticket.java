package Models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


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

	public Ticket(int ticket_id, int user_id, Severity severity, String description) {
		this.id = ticket_id;
		this.user_id = user_id;
		this.status = Status.Open;
		this.severity = severity;
		this.description = description;
		this.created_at = LocalDateTime.now();
		this.closed_at = null;
		this.archived = false;
		assignServiceDesk();
	}
	
	public int getID() {
		return this.id;
	}
	
	public int getUserID() {
		return this.user_id;
	}
	
	public LocalDateTime getCreateDate() {
		return this.created_at;
	}
	
	public LocalDateTime getClosedTime() {
		return this.closed_at;
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
	
	public Status getStatus() {
		return this.status;
	}
	
	public Boolean getArchived() {
		return this.archived;
	}
	
	public void setTechnicianId(int id) {
		this.technician_id = id;
	}
	
	public void setSeverity(Severity severity) {
		this.severity = severity;
	}
	
	public void setStatus(Status status) {
		this.status = status;
		this.closed_at = (status != Status.Open)  ? LocalDateTime.now() : null;
	}
	
	public void archiveTicket() {
		this.archived = true;
	}

	private void assignServiceDesk() {
		if (this.severity.equals(Severity.High)) {
			this.serviceDesk = 2;
		} else {		
			this.serviceDesk = 1;
		}
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
	
	public void printStaffTicket(String technician_name) {
		DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy h:mma");
		
		System.out.println(String.format("| %-10d | %-25s | %-10s | %-25s | %-60s", 
				this.id , Formatter.format(this.created_at), this.severity, technician_name , this.description));
	}
	
	public void printTechnicianTicket(int counter) {
		DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy h:mma");
		
		System.out.println(String.format("| %-10d | %-10d | %-25s | %-10s | %-25s | %-15s | %-60s", 
				counter , this.id ,Formatter.format(this.created_at), this.severity, this.status,this.archived.toString(), this.description));
	}



}
