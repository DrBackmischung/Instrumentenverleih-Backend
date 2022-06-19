package de.wi2020sebgroup1.instrumentenverleih.entities;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="verleih")
public class VerleihObjekt {
	
	@Id
	@Column(columnDefinition= "VARBINARY(16)")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "instrument_id", referencedColumnName = "id")
	private Instrument instrument;
	
	@Column
	@NotNull
	private Date bookingDate;
	
	@Column
	@NotNull
	private Date appxReturnDate;
	
	public VerleihObjekt() {}

	public VerleihObjekt(@NotNull Instrument instrument, @NotNull Date bookingDate, @NotNull Date appxReturnDate) {
		super();
		this.instrument = instrument;
		this.bookingDate = bookingDate;
		this.appxReturnDate = appxReturnDate;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Instrument getInstrument() {
		return instrument;
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Date getAppxReturnDate() {
		return appxReturnDate;
	}

	public void setAppxReturnDate(Date appxReturnDate) {
		this.appxReturnDate = appxReturnDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appxReturnDate == null) ? 0 : appxReturnDate.hashCode());
		result = prime * result + ((bookingDate == null) ? 0 : bookingDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((instrument == null) ? 0 : instrument.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VerleihObjekt other = (VerleihObjekt) obj;
		if (appxReturnDate == null) {
			if (other.appxReturnDate != null)
				return false;
		} else if (!appxReturnDate.equals(other.appxReturnDate))
			return false;
		if (bookingDate == null) {
			if (other.bookingDate != null)
				return false;
		} else if (!bookingDate.equals(other.bookingDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (instrument == null) {
			if (other.instrument != null)
				return false;
		} else if (!instrument.equals(other.instrument))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VerleihObjekt [id=" + id + ", instrument=" + instrument + ", bookingDate=" + bookingDate
				+ ", appxReturnDate=" + appxReturnDate + "]";
	}

}
