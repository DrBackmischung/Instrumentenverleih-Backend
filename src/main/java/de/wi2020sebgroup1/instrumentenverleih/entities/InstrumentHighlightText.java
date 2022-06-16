package de.wi2020sebgroup1.instrumentenverleih.entities;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="idt")
public class InstrumentHighlightText {
	
	@Id
	@Column(columnDefinition= "VARBINARY(16)")
	private UUID id;
	
	@Column
	@NotNull
	private String text;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "insturment_id", referencedColumnName = "id")
	private Instrument instrument;
	
	public InstrumentHighlightText() {
		
	}

	public InstrumentHighlightText(@NotNull String text, Instrument instrument) {
		super();
		this.text = text;
		this.instrument = instrument;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Instrument getInstrument() {
		return instrument;
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((instrument == null) ? 0 : instrument.hashCode());
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
		InstrumentHighlightText other = (InstrumentHighlightText) obj;
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
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InstrumentHighlightText [id=" + id + ", text=" + text + ", instrument=" + instrument + "]";
	}

}
