package Menu;

import Models.App;
import Auth.Login;
import Auth.Register;
import Auth.PasswordRecovery;


public class Main {	
	
	public static void menu(App App) {
		int menu_selection = 1;
		
		do {
			// Print main menu
			Main.print_menu();
			// Request selection from user then buffer scan line
			menu_selection = App.scanner.nextInt();
			App.scanner.nextLine();
			
			// Action selection
			switch(menu_selection) {
				case 1 :  Login.userLogin(App); break;
				case 2 :  PasswordRecovery.Recover(App); break;
				case 3 :  Register.RegisterNewUser(App); break;
				case 0 :  System.out.println("\r\nApplication now closing..."); break;
				default :
				System.out.println("That is not a valid option. Please select again");
			}
			System.out.print("\r\n");
		} while (menu_selection != 0);
		
		return;
	}
	
	
	/* Prints the Main Menu */
	public static void print_menu() {
		System.out.println("----------------------");
		System.out.println("Main Menu");
        System.out.println("----------------------");
        System.out.println("(1) Login");
        System.out.println("(2) Recover Password");
        System.out.println("(3) Register new staff");
        System.out.println("(0) Exit System");
        System.out.println("----------------------");
        System.out.print("Please enter a number:");
	}
	
}
