package de.wi2020sebgroup1.instrumentenverleih.entities;

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
@Table(name="idt")
public class InstrumentDetailsText {
	
	@Id
	@Column(columnDefinition= "VARBINARY(16)")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column
	@NotNull
	private String header;
	
	@Column( length = 100000 )
	@NotNull
	private String text;
	
	@Column
	@NotNull
	private String picture;
	
	//@ManyToOne
	
    //private Instrument instrument;
	
	public InstrumentDetailsText() {
		
	}

	public InstrumentDetailsText(@NotNull String header, @NotNull String text, @NotNull String picture) {
		super();
		this.header = header;
		this.text = text;
		this.picture = picture;
		//this.instrument = instrument;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	//public Instrument getInstrument() {
		//return instrument;
	//}

//	public void setInstrument(Instrument instrument) {
//		this.instrument = instrument;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((header == null) ? 0 : header.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		//result = prime * result + ((instrument == null) ? 0 : instrument.hashCode());
		result = prime * result + ((picture == null) ? 0 : picture.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		InstrumentDetailsText other = (InstrumentDetailsText) obj;
		if (header == null) {
			if (other.header != null)
				return false;
		} else if (!header.equals(other.header))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		//if (instrument == null) {
			//if (other.instrument != null)
				//return false;
		//} else if (!instrument.equals(other.instrument))
			//return false;
		if (picture == null) {
			if (other.picture != null)
				return false;
		} else if (!picture.equals(other.picture))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InstrumentDetailsText [id=" + id + ", header=" + header + ", text=" + text + ", picture=" + picture
				+ ", instrument=" + /*instrument +*/ "]";
	}
	
	

}
