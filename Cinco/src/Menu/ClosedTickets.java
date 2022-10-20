package Menu;

import java.util.List;
import java.util.stream.Collectors;

import Models.App;
import Models.Status;
import Models.Ticket;
import Models.Utility;

public class ClosedTickets {
	
	public static void menu(App App) {
		int menu_selection;
				
		// Filter current technician's tickets for those that are open
		List<Ticket> tickets = App.getTickets()
								  .stream()
								  .filter(x -> x.getStatus() != Status.Open)
								  .collect(Collectors.toList());
		
		// If there are no tickets display message and return
		if(tickets.size() == 0) {
			Utility.print_error("There are currently no closed tickets in the system.");
			return;
		}
		
		do {
			print_menu(tickets);
			
			// Request selection from user then buffer scan line
			menu_selection = App.scanner.nextInt();
			App.scanner.nextLine();
			
			// Display menu for the selected ticket.
			if(menu_selection > 0 && menu_selection <= tickets.size()) {
				TicketMenu.menu(App, tickets.get(menu_selection - 1));
			}
			else if (menu_selection < 0 || menu_selection > tickets.size()) {
				System.out.println("That is not a valid option. Please select again");
			}
			else if(tickets.get(menu_selection).getArchived() == true) {
				Utility.print_error("You cannot edit a ticket that has been archived.");
			}

		} while(menu_selection != 0);
		
		
		return;
		
	}
	
	private static void print_menu(List<Ticket> openTickets) {
		int counter = 1;
        
		Utility.print_hr(100);
		System.out.println("Display All Closed and Archived tickets.");
		Utility.print_hr(200);
		System.out.println(String.format("| %-10s | %-10s | %-25s | %-10s | %-25s | %-15s | %-60s ",
				  "Option #","Ticket ID", "Date Submitted","Severity","Status","Archived","Description"));
		Utility.print_hr(200);
        
		for(Ticket t : openTickets) {
			t.printTechnicianTicket(counter);
			counter++;
		}
        
		Utility.print_hr(200);
		System.out.println("Enter an option number to edit that ticket or 0 to return to previous menu: ");
		
		return;
	}

}
