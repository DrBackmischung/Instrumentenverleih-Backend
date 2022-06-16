package de.wi2020sebgroup1.instrumentenverleih.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="idt")
public class InstrumentDetailsText {
	
	@Id
	@Column(columnDefinition= "VARBINARY(16)")
	private UUID id;
	
	@Column
	@NotNull
	private String text;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable
    private List<Instrument> instruments = new ArrayList<>();
	
	public InstrumentDetailsText() {
		
	}

	public InstrumentDetailsText(@NotNull String text, List<Instrument> instrument) {
		super();
		this.text = text;
		this.instruments = instrument;
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

	public List<Instrument> getInstrument() {
		return instruments;
	}

	public void setInstrument(List<Instrument> instrument) {
		this.instruments = instrument;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((instruments == null) ? 0 : instruments.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (instruments == null) {
			if (other.instruments != null)
				return false;
		} else if (!instruments.equals(other.instruments))
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
		return "InstrumentDetailsText [id=" + id + ", text=" + text + ", instrument=" + instruments + "]";
	}

}
