package Menu;

import Models.App;
import Models.Ticket;


public class TicketMenu {
	
	public static void menu(App App, Ticket ticket) {
		int menu_selection = 1;

		do {
			// Print tickets 
			TicketMenu.print_menu();
			
			// Request selection from user then buffer scan line
			menu_selection = App.scanner.nextInt();
			App.scanner.nextLine();
			
			// Action selection
			switch(menu_selection) { 
				case 1 :  StatusMenu.menu(App, ticket); break;
				case 2 :  SeverityMenu.menu(App, ticket); break;
				case 0 :  continue;
				default : System.out.println("That is not a valid option. Please select again");
			}
		} while(menu_selection != 0);
		
		return;   
	}
	
	public static void print_menu() {	
		System.out.print("\r\n");
		System.out.println("----------------------");
		System.out.println("Ticket Menu");
        System.out.println("----------------------");
        System.out.println("(1) Modify status.");
        System.out.println("(2) Modify severity.");
        System.out.println("(0) Return to Ticket List");
        System.out.println("----------------------");
        System.out.print("Please select an option :");
	}
	

}
