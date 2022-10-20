import Models.App;
import Models.Severity;
import Models.Ticket;
import Models.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import Menu.Main;

public class Cinco {

	public static void main(String[] args) {
		// Initialise the app
		App App = new App();	
	
		// Progress to main menu
		Main.menu(App);
		
		// Close down app
		App.scanner.close();
		System.exit(1);
	}
	

}
