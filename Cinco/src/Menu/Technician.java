package Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Models.App;
import Models.Role;
import Models.Severity;
import Models.Status;
import Models.Ticket;
import Models.User;

public class Technician {
	
	public static void menu(App App) {
		User currentUser = App.getCurrentUser();
		int menu_selection = 1;

		do {
			// Print Staff Menu
			print_menu();
			
			// Request selection from user then buffer scan line
			menu_selection = App.scanner.nextInt();
			App.scanner.nextLine();
			
			// Action selection
			switch(menu_selection) { 
				case 1 :  System.out.println("Option 1 selected."); ticketMenu(App); break;
				case 2 :  System.out.println("Option 2 selected."); break;
				case 3 :  System.out.println("Option 3 selected."); break;
				case 0 :  App.logout();
				  		  System.out.println("Logging out and returning to main menu..."); 
				  		  break;
				default : System.out.println("That is not a valid option. Please select again");
			}
		} while(menu_selection != 0);
		
		return;   
	}
	
	/* Prints the Technician Menu */
	private static void print_menu() {
		System.out.print("\r\n");
		System.out.println("----------------------");
		System.out.println("Technician Menu");
        System.out.println("----------------------");
        System.out.println("(1) View assigned tickets.");
        System.out.println("(2) View all closed tickets.");
        System.out.println("(3) View closed tickets.");
        System.out.println("(0) Log Out");
        System.out.println("----------------------");
        System.out.print("Please select an option :");
	}
	
	private static Ticket printTickets(App App) {
		User currentUser = App.getCurrentUser();
		ArrayList<Ticket> tickets = currentUser.getTickets(); 
		// Filter current technician's tickets for those that are open
		List<Ticket> openTickets = tickets.stream().filter(x -> x.getStatus() == Status.Open).collect(Collectors.toList());
		int counter = 1;
		int menu_selection;
		
		// Print open tickets as menu
		System.out.print("\r\n");
		System.out.println("----------------------");
		System.out.println("Ticket Menu");
        System.out.println("----------------------");
        
		for(Ticket t : openTickets) {
			System.out.println("(" + counter + ") " + t.getTechnicianId() + t.getDescription() + t.getStatus() + t.getSeverity());
			counter++;
		}
		
		System.out.println("Enter a number to edit a ticket: ");
		
		// Request selection from user then buffer scan line
		menu_selection = App.scanner.nextInt();
		App.scanner.nextLine();
		
		return tickets.get(menu_selection - 1);
	}
	
	public static void ticketMenu(App App) {
		User currentUser = App.getCurrentUser();
		int menu_selection = 1;

		do {
			// Print Staff Menu
			Ticket selectedTicket = printTickets(App);
			
			// Print tickets 
			printTicketMenu();
			
			// Request selection from user then buffer scan line
			menu_selection = App.scanner.nextInt();
			App.scanner.nextLine();
			
			// Action selection
			switch(menu_selection) { 
				case 1 :  selectedTicket.setStatus(App); break;
				case 2 :  selectedTicket.setSeverity(App); break;
				case 0 :  continue;
				default : System.out.println("That is not a valid option. Please select again");
			}
		} while(menu_selection != 0);
		
		return;   
	}
	
	private static void printTicketMenu() {	
		System.out.print("\r\n");
		System.out.println("----------------------");
		System.out.println("Ticket Menu");
        System.out.println("----------------------");
        System.out.println("(1) Modify status.");
        System.out.println("(2) Modify severity.");
        System.out.println("(0) Log Out");
        System.out.println("----------------------");
        System.out.print("Please select an option :");
	}

}
