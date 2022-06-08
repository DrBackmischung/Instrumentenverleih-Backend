package de.wi2020sebgroup1.instrumentenverleih.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="verleih")
public class VerleihObjekt {
	
	@Id
	@Column(columnDefinition= "VARBINARY(16)")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column
	@NotNull
	private String category;
	
	@Column
	@NotNull
	private String name;
	
	@Column
	@NotNull
	private String description;
	
	@Column
	@NotNull
	private String soundLink;
	
	@Column
	@NotNull
	private int amount;
	
	public VerleihObjekt() {}

	public VerleihObjekt(UUID id, @NotNull String category, @NotNull String name, @NotNull String description,
			@NotNull String soundLink, @NotNull int amount) {
		super();
		this.id = id;
		this.category = category;
		this.name = name;
		this.description = description;
		this.soundLink = soundLink;
		this.amount = amount;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSoundLink() {
		return soundLink;
	}

	public void setSoundLink(String soundLink) {
		this.soundLink = soundLink;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((soundLink == null) ? 0 : soundLink.hashCode());
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
		if (amount != other.amount)
			return false;
		if (category != other.category)
			return false;
		if (description != other.description)
			return false;
		if (id != other.id)
			return false;
		if (name != other.name)
			return false;
		if (soundLink != other.soundLink)
			return false;
		return true;
	}

}
