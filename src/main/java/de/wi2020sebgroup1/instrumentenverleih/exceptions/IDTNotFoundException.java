package de.wi2020sebgroup1.instrumentenverleih.exceptions;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class IDTNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public IDTNotFoundException(UUID id) {
		super("IDT with id \"" + id + "\" not found!");
	}

}
