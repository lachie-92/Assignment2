package Auth;


import Menu.Staff;
import Menu.Technician;
import Models.App;
import Models.User;

public class Login {
	
	public static void userLogin(App App) {
		String email;
		String password;
		Boolean Login = false;
		Boolean valid_email = false;
		Boolean valid_password = false;

		System.out.println("----------------------");
		System.out.println("User Login");
        System.out.println("----------------------");
        
        do {
        	// Prompt user for valid email and validate response.
            do {
            	System.out.println("Please enter your email address or enter 0 to return to main menu:");
            	email = App.scanner.nextLine();
            	// Check to see if user wants to return to main menu
                if(email.equals("0")) return;
                // Validate Email
            	valid_email = Validator.validateEmail(email);
            } while(valid_email == false);
            
            
            // Prompt user for valid password and validate response.
            do {
            	System.out.println("Please enter your password or enter 0 to return to main menu:");
            	password = App.scanner.nextLine();
            	// Check to see if user wants to return to main menu
                if(password.equals("0")) return;
                // Validate password
            	valid_password = Validator.validatePassword(password);
            } while(valid_password == false);
            
            
            // Try to log in with the entered username and password.
            if(verifyLogin(App, email, password)) {
            	Login = true;
            	// Route user to their menu
            	switch(App.getCurrentUser().getRole()) {
            		case "Staff" : Staff.menu(App); break;
            		case "Technician" : Technician.menu(App); break;
            		default :
            			System.out.println("This user does not have a current role and therefore cannot be logged in. Please contact IT Department");
            			App.logout();
            			break;
            	}
            };
        } while(Login == false);
        

        
        return;
		
	}

	/* Check to see if there is a match in the system for an entered email and password and if found then sets the current App user*/
	public static Boolean verifyLogin(App App, String email, String password) {
		for (User user : App.getUsers()) {
			if(user.getEmail().equals(email) && user.getPassword().equals(password)) {
				// Set current user and return true
				System.out.println("Welcome " + user.getName());
				App.setCurrentUser(user);
				return true;
			}
		}
		// User has not been found or incorrect credentials.
		System.out.println("These credientials do not match our records. Please try again..");
		System.out.println();
		return false;
	}


	





	
}
