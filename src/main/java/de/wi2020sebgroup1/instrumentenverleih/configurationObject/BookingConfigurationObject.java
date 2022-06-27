package de.wi2020sebgroup1.instrumentenverleih.configurationObject;

import java.sql.Date;
import java.util.UUID;

public class BookingConfigurationObject {

	public Date bookingDate;
	public Date apprxReturnDate;
	public UUID userID;
	public UUID instrumentID;
	public byte[] signatureCode;
	
	public BookingConfigurationObject(Date bookDate, Date apprxReturnDate, UUID userID, UUID instrumentID, byte[] signatureCode) {
		this.userID = userID;
		this.apprxReturnDate = apprxReturnDate;
		this.bookingDate = bookDate;
		this.instrumentID = instrumentID;
		this.signatureCode = signatureCode;
	}

}
