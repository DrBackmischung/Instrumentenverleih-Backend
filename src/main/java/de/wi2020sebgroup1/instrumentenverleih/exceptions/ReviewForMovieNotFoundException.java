package de.wi2020sebgroup1.instrumentenverleih.exceptions;

import java.util.UUID;

public class ReviewForMovieNotFoundException extends Exception {
	
	
	private static final long serialVersionUID = 1L;
	
	public ReviewForMovieNotFoundException(UUID id) {
		super("No Reviews for Movie " + id + " found!");
	}

}
