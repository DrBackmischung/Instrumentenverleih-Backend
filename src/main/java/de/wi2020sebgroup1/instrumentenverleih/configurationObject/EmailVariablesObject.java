package de.wi2020sebgroup1.instrumentenverleih.configurationObject;

public class EmailVariablesObject {
	
	private String username;
	private String firstName;
	private String lastName;
	private String link;
	private String file;
	private String category;
	private String instrument;
	private String bookingDate;
	private String string1;
	private String string2;
	private String string3;
	
	public EmailVariablesObject(String username, String firstName, String lastName, String link, String file,
			String category, String instrument, String bookingDate, String string1, String string2, String string3) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.link = link;
		this.file = file;
		this.category = category;
		this.instrument = instrument;
		this.bookingDate = bookingDate;
		this.string1 = string1;
		this.string2 = string2;
		this.string3 = string3;
	}

	public String getUsername() {
		return username;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getLink() {
		return link;
	}

	public String getFile() {
		return file;
	}

	public String getString1() {
		return string1;
	}

	public String getString2() {
		return string2;
	}


	public String getString3() {
		return string3;
	}

	public String getCategory() {
		return category;
	}

	public String getInstrument() {
		return instrument;
	}

	public String getBookingDate() {
		return bookingDate;
	}
	
}
