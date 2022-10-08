import Models.App;
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
