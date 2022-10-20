package Seeders;

import Models.App;
import Models.Severity;
import Models.Ticket;

public class TicketSeeder {
	
	public static void seed_tickets(App App) {
		
		// Low
		Ticket ticket_1 = new Ticket(App.getTickets().size(), 5, Severity.Low ,"Low Severity Ticket");
		App.storeNewTicket(ticket_1);
		App.assignTicketToTechnician(ticket_1);
		
		// Medium
		Ticket ticket_2 = new Ticket(App.getTickets().size(), 5, Severity.Medium ,"Medium Severity Ticket");
		App.storeNewTicket(ticket_2);
		App.assignTicketToTechnician(ticket_2);
		
		// High
		Ticket ticket_3 = new Ticket(App.getTickets().size(), 5, Severity.High ,"High Severity Ticket");
		App.storeNewTicket(ticket_3);
		App.assignTicketToTechnician(ticket_3);
	}

}
