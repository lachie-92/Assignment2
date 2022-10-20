package Reports;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import Models.App;
import Models.Status;
import Models.Ticket;
import Models.User;
import Models.Utility;

public class TicketReport {
	
	private LocalDateTime start_date, end_date;
	private int total_tickets, open_tickets, closed_tickets;
	private ArrayList<Ticket> tickets;
	private DateTimeFormatter formatter;
	
	public TicketReport() {
		this.formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy h:mma");
		this.tickets = new ArrayList<Ticket>();
		this.total_tickets = 0;
		this.open_tickets = 0;
		this.closed_tickets = 0;
		this.start_date = null;
		this.end_date = null;		
	}

	public static void getReport(App App) {
		String start_date,end_date;
		Boolean valid_startdate = false, valid_enddate = false;
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		
		if(App.getTickets().size() == 0) {
			Utility.print_error("There are currently no existing tickets in the system.");
			return;
		}
		
		TicketReport report = new TicketReport();
		
		
        	// Prompt user for valid email and validate response.
			System.out.println("Please enter the Start Date for the report in the format of dd-MM-yyyy (eg.12-03-2022) :");
            do {
            	start_date = App.scanner.nextLine();
            	// validate Date
            	
                try {
                	report.start_date = LocalDateTime.parse(start_date + " 00:00:00", dateFormatter);
                	valid_startdate = true;
                } catch (DateTimeParseException e) {
                	System.out.println("This is not a valid date. Please try again in the format of dd-MM-yyyy");
                }
            } while(valid_startdate == false);
           
            
            
        	// Prompt user for valid email and validate response.
            System.out.println("Please enter the End Date for the report in the format of dd-MM-yyyy:");
            do {
            	// Get user input
            	end_date = App.scanner.nextLine();
            	// Validate Date
                // Set start date
                try {
                	report.end_date = LocalDateTime.parse(end_date + " 23:59:59", dateFormatter);
                	valid_enddate = true;
                } catch (DateTimeParseException e) {
                	System.out.println("This is not a valid date. Please try again in the format of dd-MM-yyyy");
                }
                
            } while(valid_enddate == false);
            
            // Calculate Report Data
            report.getReportData(App);
            
            // Print report
            report.printReportData(App);
		
            return;
	}
	
	private void getReportData(App App) {

		// If there are no tickets in the system, display message to user and return
		if(App.getTickets().size() == 0) {
			Utility.print_error("There are currently no existing tickets in the system.");
			return;
		}

		// Iterate over tickets and filter tickets between the start and end date
		for(Ticket ticket : App.getTickets()) {
			// For tickets between two dates
			if( ticket.getCreateDate().isAfter(this.start_date) && ticket.getCreateDate().isBefore(this.end_date))
			{
				this.tickets.add(ticket);
				// Increase total ticket count
				this.total_tickets += 1;
				
				// If ticket is open, increase open count
				if(ticket.getStatus().equals(Status.Open)) {
					this.open_tickets += 1;
				}
				// Otherwise Increase closed ticket count
				else {
					this.closed_tickets += 1;
				}
			}	
		}
		
		return;
	}
	
	public void printReportData(App App) {
		List<User> Users = App.getUsers();
		
		// If there are no tickets display error and return
		if(this.tickets.size() == 0) {
			Utility.print_error("There are currently no existing tickets in the system between these dates.");
			return;
		}
		
		// Print ticket summary
		print_summary();
		// Print Ticket Header Row
		print_header();

		// Iterate over each ticket and print ticket details
		for(Ticket ticket : this.tickets) {
			print_ticket_details(ticket, Users.get(ticket.getUserID()), Users.get(ticket.getTechnicianId()));
		}
		
		Utility.print_hr(175);
		
		return;
	}
	
	private void print_summary() {
		// Print the ticket summary
		Utility.print_hr(25);
		System.out.println("Ticket Report Summary");
		Utility.print_hr(25);
		System.out.println("Total Tickets: " + this.total_tickets);
		System.out.println("Open Tickets : " + this.open_tickets);
		System.out.println("Closed Tickets: "+ this.closed_tickets);
		System.out.println();
	}
	
	private void print_header() {
		System.out.println("Ticket Report Details");
		Utility.print_hr(175);
		System.out.println(String.format("| %-10s | %-20s | %-20s | %-10s | %-20s | %-25s | %-20s ",
						  "Ticket ID","Submitted By","Date Submitted","Severity","Attended By","Status","Time To Close Ticket"));
		Utility.print_hr(175);
	}
	
	private void print_ticket_details(Ticket ticket, User user, User technician) {
		// Initialise close date in case there is currently no closed date for the ticket.
		String closeTime = (ticket.getClosedTime() == null) ? "N/A" : Utility.time_between_dates(ticket.getCreateDate(),ticket.getClosedTime());
		
		System.out.println(String.format("| %-10s | %-20s | %-20s | %-10s | %-20s | %-25s | %-20s", 
				ticket.getID(), user.getName(), this.formatter.format(ticket.getCreateDate()), ticket.getSeverity().toString(), technician.getName(), ticket.getStatus(), closeTime));
	}
	
	
}
