package de.wi2020sebgroup1.instrumentenverleih.entities;

import java.sql.Date;
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
	private boolean active;
	
	@Column
	@Lob
	private byte[] qrCode;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "verleih_id", referencedColumnName = "id")
	private VerleihObjekt vo;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	public Booking() {
		
	}
	
	public Booking(@NotNull UUID id,@NotNull Date bookingDate,
			@NotNull User user, @NotNull VerleihObjekt vo) {
		this.id = id;
		this.bookingDate = bookingDate;
		this.user = user;
		this.vo = vo;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public User getUser() {
		return user;
	}
	
	public Date getBookingDate() {
		return bookingDate;
	}

	public UUID getId() {
		return id;
	}
	
	public byte[] getQrCode() {
		return qrCode;
	}
	
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
	
	public void setQrCode(byte[] qrCode) {
		this.qrCode = qrCode;
	}
	
	public void setUser(User user) {
		this.user = user;
	}


	public VerleihObjekt getVo() {
		return vo;
	}

	public void setVo(VerleihObjekt vo) {
		this.vo = vo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookingDate == null) ? 0 : bookingDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (bookingDate != other.bookingDate)
			return false;
		if (id != other.id)
			return false;
		if (user != other.user)
			return false;
		if (vo != other.vo)
			return false;
		return true;
	}

}
