package de.wi2020sebgroup1.instrumentenverleih.configurationObject;

import java.util.List;

import de.wi2020sebgroup1.instrumentenverleih.entities.InstrumentDetailsText;
import de.wi2020sebgroup1.instrumentenverleih.entities.InstrumentHighlightText;

public class InstrumentConfigurationObject {

	public String title;
	public String category;
	public String mainText;
	public String mainPicture;
	public String example;
	public String highlightBackground;
	public String highlightText;
	public List<InstrumentHighlightText> highlightList;
	public List<InstrumentDetailsText> detailSections;
	
	public InstrumentConfigurationObject(String title, String category, String mainText, String mainPicture,
			String example, String highlightBackground, String highlightText,
			List<InstrumentHighlightText> highlightList, List<InstrumentDetailsText> detailSections) {
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
	}

}
