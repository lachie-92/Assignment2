package Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Models.App;
import Models.Status;
import Models.Ticket;
import Models.User;
import Models.Utility;

public class AssignedTickets {

	public static void menu(App App) {
		User currentUser = App.getCurrentUser();
		ArrayList<Ticket> tickets = currentUser.getTickets();
		int menu_selection;
				
		// Filter current technician's tickets for those that are open
		List<Ticket> openTickets = tickets.stream().filter(x -> x.getStatus() == Status.Open).collect(Collectors.toList());
		
		// If there are no tickets display message and return
		if(openTickets.size() == 0) {
			Utility.print_error("There are currently no open tickets for this user.");
			return;
		}
		
		Utility.print_hr(100);
		System.out.println("Assigned Tickets : " + currentUser.getName());

		do {
			print_menu(openTickets);
			
			// Request selection from user then buffer scan line
			menu_selection = App.scanner.nextInt();
			App.scanner.nextLine();
			
			// Display menu for the selected ticket.
			if(menu_selection > 0 && menu_selection <= openTickets.size()) {
				TicketMenu.menu(App, tickets.get(menu_selection - 1));
			}
			else if (menu_selection < 0 || menu_selection > openTickets.size()) {
				System.out.println("That is not a valid option. Please select again");
			}

		} while(menu_selection != 0);
		
		
		return;
		
	}
	
	private static void print_menu(List<Ticket> openTickets) {
		int counter = 1;
        
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
