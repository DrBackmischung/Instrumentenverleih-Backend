package de.wi2020sebgroup1.instrumentenverleih.entities;

import java.sql.Date;
import java.util.Arrays;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="rentals")
public class Booking {
	
	@Id
	@Column(columnDefinition= "VARBINARY(16)")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column
	@NotNull
	private Date bookingDate;
	
	@Column
	@NotNull
	private Date apprxReturnDate;
	
	@Column
	@NotNull
	private boolean active;
	
	@Column
	@Lob
	private byte[] qrCode;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "instrument_id", referencedColumnName = "id")
	private Instrument vo;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	public Booking() {
		
	}

	public Booking(UUID id, @NotNull Date bookingDate, @NotNull Date apprxReturnDate, Instrument vo, User user) {
		super();
		this.id = id;
		this.bookingDate = bookingDate;
		this.apprxReturnDate = apprxReturnDate;
		this.vo = vo;
		this.user = user;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Date getApprxReturnDate() {
		return apprxReturnDate;
	}

	public void setApprxReturnDate(Date apprxReturnDate) {
		this.apprxReturnDate = apprxReturnDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public byte[] getQrCode() {
		return qrCode;
	}

	public void setQrCode(byte[] qrCode) {
		this.qrCode = qrCode;
	}

	public Instrument getVo() {
		return vo;
	}

	public void setVo(Instrument vo) {
		this.vo = vo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((apprxReturnDate == null) ? 0 : apprxReturnDate.hashCode());
		result = prime * result + ((bookingDate == null) ? 0 : bookingDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Arrays.hashCode(qrCode);
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((vo == null) ? 0 : vo.hashCode());
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
		Booking other = (Booking) obj;
		if (active != other.active)
			return false;
		if (apprxReturnDate == null) {
			if (other.apprxReturnDate != null)
				return false;
		} else if (!apprxReturnDate.equals(other.apprxReturnDate))
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
		if (!Arrays.equals(qrCode, other.qrCode))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (vo == null) {
			if (other.vo != null)
				return false;
		} else if (!vo.equals(other.vo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", bookingDate=" + bookingDate + ", apprxReturnDate=" + apprxReturnDate
				+ ", active=" + active + ", qrCode=" + Arrays.toString(qrCode) + ", vo=" + vo + ", user=" + user + "]";
	}

}
