package de.wi2020sebgroup1.instrumentenverleih.configurationObject;

import java.util.List;
import java.util.UUID;

public class InstrumentConfigurationObject {

	public String title;
	public String category;
	public String mainText;
	public String mainPicture;
	public String example;
	public String highlightBackground;
	public String highlightText;
	public List<UUID> highlightList;
	public List<UUID> detailSections;
	
	public InstrumentConfigurationObject(String title, String category, String mainText, String mainPicture,
			String example, String highlightBackground, String highlightText,
			List<UUID> highlightList, List<UUID> detailSections) {
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
