package Menu;

import Models.App;
import Models.Ticket;

public class Staff {
	
	
	
	public static void menu(App App) {

		int menu_selection = 1;
		
		
		do {
			// Print Staff Menu
			print_menu();
			// Request selection from user then buffer scan line
			menu_selection = App.scanner.nextInt();
			App.scanner.nextLine();
			
			// Action selection
			switch(menu_selection) { 
				case 1 :  Ticket.create(App); break;
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
	
	/* Prints the Staff Menu */
	private static void print_menu() {
		System.out.print("\r\n");
		System.out.println("----------------------");
		System.out.println("Staff Menu");
        System.out.println("----------------------");
        System.out.println("(1) Create new ticket.");
        System.out.println("(2) View open tickets.");
        System.out.println("(0) Log Out");
        System.out.println("----------------------");
        System.out.print("Please select an option :");
	}

}
