package de.wi2020sebgroup1.instrumentenverleih.controller;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.wi2020sebgroup1.instrumentenverleih.configurationObject.BookingConfigurationObject;
import de.wi2020sebgroup1.instrumentenverleih.configurationObject.EmailVariablesObject;
import de.wi2020sebgroup1.instrumentenverleih.entities.Booking;
import de.wi2020sebgroup1.instrumentenverleih.entities.Instrument;
import de.wi2020sebgroup1.instrumentenverleih.entities.User;
import de.wi2020sebgroup1.instrumentenverleih.exceptions.BookingNotFoundException;
import de.wi2020sebgroup1.instrumentenverleih.repositories.BookingRepositroy;
import de.wi2020sebgroup1.instrumentenverleih.repositories.InstrumentRepository;
import de.wi2020sebgroup1.instrumentenverleih.repositories.UserRepository;
import de.wi2020sebgroup1.instrumentenverleih.services.EmailService;
import de.wi2020sebgroup1.instrumentenverleih.services.QRCodeGenerator;

@Controller
@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	BookingRepositroy bookingRepositroy;
	
	@Autowired
	UserRepository userRepositroy;
	
	@Autowired
	QRCodeGenerator qrCodeGenerator;
	
	@Autowired
	InstrumentRepository instrumentRepository;
	
	@Autowired
	EmailService emailService;
	
	@SuppressWarnings({ "static-access" })
	@PutMapping("/add")
	@Transactional
	public ResponseEntity<Object> addBooking(@RequestBody BookingConfigurationObject bookingObject){
		
		Instrument vo = instrumentRepository.findById(bookingObject.instrumentID).get();
		if(vo.getAmount() <= 0) {
			return new ResponseEntity<Object>("Not enough instruments!", HttpStatus.CONFLICT);
		}
			try {
				User user = userRepositroy.findById(bookingObject.userID).get();
				UUID bookingId = UUID.randomUUID();
				
				Booking booking = new Booking(bookingId, bookingObject.apprxReturnDate, bookingObject.bookingDate, vo, user);
				booking.setActive(true);
				byte[] qrCode = qrCodeGenerator.generateQRCode(booking.getId().toString());
				booking.setQrCode(qrCode);
				booking.setSignatureCode(bookingObject.signatureCode);

				emailService.sendMailBooking(
						user.getEmail(),
						"Buchung bestätigt!",
						new EmailVariablesObject(user.getUserName(), user.getFirstName(), user.getName(), "", "", vo.getCategory(), vo.getTitle(), "", "", "", ""),
						"Booking.html",
						qrCode
				);
				
				vo.setAmount(vo.getAmount() - 1);
				instrumentRepository.save(vo);
				
				return new ResponseEntity<Object>(bookingRepositroy.save(booking), HttpStatus.CREATED);
			} catch(Exception e) {
				
				return new ResponseEntity<Object>(e.getMessage(),HttpStatus.CONFLICT);
			}
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<Object> getAll(){
		return new ResponseEntity<Object>(bookingRepositroy.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getSpecific(@PathVariable UUID id){
		try {
			return new ResponseEntity<Object>(bookingRepositroy.findById(id).get(), HttpStatus.OK);
		}
		catch(NoSuchElementException e) {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable UUID id){
		
		try {
			Optional<Booking> o = bookingRepositroy.findById(id);
			bookingRepositroy.deleteById(o.get().getId());
			return new ResponseEntity<>(id, HttpStatus.OK);
		} catch (NoSuchElementException nSE) {
			return new ResponseEntity<Object>(new BookingNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/return/{id}")
	public ResponseEntity<Object> addBooking(@PathVariable UUID id){
		
		try {
			Optional<Booking> o = bookingRepositroy.findById(id);
			Booking b = o.get();
			b.setActive(false);
			b.getVo().setAmount(b.getVo().getAmount() + 1);
			bookingRepositroy.save(b);
			return new ResponseEntity<>(id, HttpStatus.OK);
		} catch (NoSuchElementException nSE) {
			return new ResponseEntity<Object>(new BookingNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}

}
