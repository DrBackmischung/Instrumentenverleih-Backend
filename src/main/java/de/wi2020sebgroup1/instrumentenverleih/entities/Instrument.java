package de.wi2020sebgroup1.instrumentenverleih.entities;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="instrument")
public class Instrument {
	
	@Id
	@Column(columnDefinition= "VARBINARY(16)")
	private UUID id;
	
	@Column
	@NotNull
	private String title;
	
	@Column
	@NotNull
	private String category;
	
	@Column
	@NotNull
	private String mainText;
	
	@Column
	@NotNull
	private String mainPicture;
	
	@Column
	@NotNull
	private String example;
	
	@Column
	@NotNull
	private String highlightBackground;
	
	@Column
	@NotNull
	private String highlightText;

	@OneToMany(mappedBy = "id")
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private List<InstrumentHighlightText> highlightList;

	@OneToMany(mappedBy = "id")
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private List<InstrumentDetailsText> detailSections;

	@OneToMany(mappedBy = "id")
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private List<VerleihObjekt> verleihListe;
	
	@Column
	@NotNull
	private int amount;
	
	public Instrument() {
		
	}

	public Instrument(@NotNull String title, @NotNull String category, @NotNull String mainText,
			@NotNull String mainPicture, @NotNull String example, @NotNull String highlightBackground,
			@NotNull String highlightText, List<InstrumentHighlightText> highlightList,
			List<InstrumentDetailsText> detailSections, List<VerleihObjekt> verleihListe, @NotNull int amount) {
		super();
		this.title = title;
		this.category = category;
		this.mainText = mainText;
		this.mainPicture = mainPicture;
		this.example = example;
		this.highlightBackground = highlightBackground;
		this.highlightText = highlightText;
		this.highlightList = highlightList;
		this.detailSections = detailSections;
		this.verleihListe = verleihListe;
		this.amount = amount;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getMainText() {
		return mainText;
	}

	public void setMainText(String mainText) {
		this.mainText = mainText;
	}

	public String getMainPicture() {
		return mainPicture;
	}

	public void setMainPicture(String mainPicture) {
		this.mainPicture = mainPicture;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public String getHighlightBackground() {
		return highlightBackground;
	}

	public void setHighlightBackground(String highlightBackground) {
		this.highlightBackground = highlightBackground;
	}

	public String getHighlightText() {
		return highlightText;
	}

	public void setHighlightText(String highlightText) {
		this.highlightText = highlightText;
	}

	public List<InstrumentHighlightText> getHighlightList() {
		return highlightList;
	}

	public void setHighlightList(List<InstrumentHighlightText> highlightList) {
		this.highlightList = highlightList;
	}

	public List<InstrumentDetailsText> getDetailSections() {
		return detailSections;
	}

	public void setDetailSections(List<InstrumentDetailsText> detailSections) {
		this.detailSections = detailSections;
	}

	public List<VerleihObjekt> getVerleihListe() {
		return verleihListe;
	}

	public void setVerleihListe(List<VerleihObjekt> verleihListe) {
		this.verleihListe = verleihListe;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void addIDT(InstrumentDetailsText idt) {
		this.detailSections.add(idt);
	}
	
	public void addIHT(InstrumentHighlightText iht) {
		this.highlightList.add(iht);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((detailSections == null) ? 0 : detailSections.hashCode());
		result = prime * result + ((example == null) ? 0 : example.hashCode());
		result = prime * result + ((highlightBackground == null) ? 0 : highlightBackground.hashCode());
		result = prime * result + ((highlightList == null) ? 0 : highlightList.hashCode());
		result = prime * result + ((highlightText == null) ? 0 : highlightText.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mainPicture == null) ? 0 : mainPicture.hashCode());
		result = prime * result + ((mainText == null) ? 0 : mainText.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((verleihListe == null) ? 0 : verleihListe.hashCode());
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
		Instrument other = (Instrument) obj;
		if (amount != other.amount)
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (detailSections == null) {
			if (other.detailSections != null)
				return false;
		} else if (!detailSections.equals(other.detailSections))
			return false;
		if (example == null) {
			if (other.example != null)
				return false;
		} else if (!example.equals(other.example))
			return false;
		if (highlightBackground == null) {
			if (other.highlightBackground != null)
				return false;
		} else if (!highlightBackground.equals(other.highlightBackground))
			return false;
		if (highlightList == null) {
			if (other.highlightList != null)
				return false;
		} else if (!highlightList.equals(other.highlightList))
			return false;
		if (highlightText == null) {
			if (other.highlightText != null)
				return false;
		} else if (!highlightText.equals(other.highlightText))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mainPicture == null) {
			if (other.mainPicture != null)
				return false;
		} else if (!mainPicture.equals(other.mainPicture))
			return false;
		if (mainText == null) {
			if (other.mainText != null)
				return false;
		} else if (!mainText.equals(other.mainText))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (verleihListe == null) {
			if (other.verleihListe != null)
				return false;
		} else if (!verleihListe.equals(other.verleihListe))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Instrument [id=" + id + ", title=" + title + ", category=" + category + ", mainText=" + mainText
				+ ", mainPicture=" + mainPicture + ", example=" + example + ", highlightBackground="
				+ highlightBackground + ", highlightText=" + highlightText + ", highlightList=" + highlightList
				+ ", detailSections=" + detailSections + ", verleihListe=" + verleihListe + ", amount=" + amount + "]";
	}

}
