package de.wi2020sebgroup1.instrumentenverleih.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.wi2020sebgroup1.instrumentenverleih.repositories.InstrumentRepository;

@Controller
@RestController
@RequestMapping("/instrumentdetailstext")
public class InstrumentDetailsTextController {
	
	@Autowired
	InstrumentRepository instrumentRepository;

}
