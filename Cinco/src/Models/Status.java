package Models;

public enum Status {
	
	Open("Open"),
	CR("Closed and Resolved"),
	CU("Closed and Unresolved");
	
	private final String text;
	
	Status(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
	
	
}
