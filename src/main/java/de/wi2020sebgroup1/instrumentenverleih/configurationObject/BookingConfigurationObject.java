package de.wi2020sebgroup1.instrumentenverleih.configurationObject;

import java.sql.Date;
import java.util.UUID;

public class BookingConfigurationObject {
	
	public Date bookingDate;
	public UUID userID;
	public UUID voID;
	
	public BookingConfigurationObject(Date bookDate, UUID userID, UUID voID) {
		this.userID = userID;
		this.bookingDate = bookDate;
		this.voID = voID;
	}

}
