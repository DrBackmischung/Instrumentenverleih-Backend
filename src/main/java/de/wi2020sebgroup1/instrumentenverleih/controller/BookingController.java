package de.wi2020sebgroup1.instrumentenverleih.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.wi2020sebgroup1.instrumentenverleih.configurationObject.BookingConfigurationObject;
import de.wi2020sebgroup1.instrumentenverleih.configurationObject.EmailVariablesObject;
import de.wi2020sebgroup1.instrumentenverleih.entities.Booking;
import de.wi2020sebgroup1.instrumentenverleih.entities.Price;
import de.wi2020sebgroup1.instrumentenverleih.entities.Seat;
import de.wi2020sebgroup1.instrumentenverleih.entities.Show;
import de.wi2020sebgroup1.instrumentenverleih.entities.Snack;
import de.wi2020sebgroup1.instrumentenverleih.entities.Ticket;
import de.wi2020sebgroup1.instrumentenverleih.entities.User;
import de.wi2020sebgroup1.instrumentenverleih.enums.BookingState;
import de.wi2020sebgroup1.instrumentenverleih.enums.TicketState;
import de.wi2020sebgroup1.instrumentenverleih.exceptions.SeatNotFoundException;
import de.wi2020sebgroup1.instrumentenverleih.exceptions.SnackNotFoundException;
import de.wi2020sebgroup1.instrumentenverleih.repositories.BookingRepositroy;
import de.wi2020sebgroup1.instrumentenverleih.repositories.PriceRepository;
import de.wi2020sebgroup1.instrumentenverleih.repositories.SeatRepository;
import de.wi2020sebgroup1.instrumentenverleih.repositories.ShowRepository;
import de.wi2020sebgroup1.instrumentenverleih.repositories.SnackRepository;
import de.wi2020sebgroup1.instrumentenverleih.repositories.TicketRepository;
import de.wi2020sebgroup1.instrumentenverleih.repositories.UserRepository;
import de.wi2020sebgroup1.instrumentenverleih.services.EmailService;
import de.wi2020sebgroup1.instrumentenverleih.services.QRCodeGenerator;
import de.wi2020sebgroup1.instrumentenverleih.services.SeatService;

@Controller
@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	BookingRepositroy bookingRepositroy;
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	UserRepository userRepositroy;
	
	@Autowired
	ShowRepository showRepository;
	
	@Autowired
	SeatRepository seatRepository;
	
	@Autowired
	SnackRepository snackRepository;
	
	@Autowired
	SeatService seatService;
	
	@Autowired
	QRCodeGenerator qrCodeGenerator;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	PriceRepository priceRepository;
	
	@SuppressWarnings({ "static-access", "deprecation" })
	@PutMapping("/add")
	@Transactional
	public ResponseEntity<Object> addBooking(@RequestBody BookingConfigurationObject bookingObject){

		ArrayList<Ticket> tickets = new ArrayList<>();
		ArrayList<Snack> snacks = new ArrayList<>();
		
		ArrayList<UUID> seatIDs = bookingObject.seatIDs;
		ArrayList<UUID> snackIDs = (bookingObject.snackIDs != null) ? bookingObject.snackIDs : null;
		
		Show show = showRepository.findById(bookingObject.showID).get();
		if(seatService.reserveSeats(seatIDs, bookingObject.showID)) {
			try {
				User user = userRepositroy.findById(bookingObject.userID).get();
				UUID bookingId = UUID.randomUUID();
				
				for(UUID seat : seatIDs) {
					try {
						Seat seatObject = seatRepository.findById(seat).get();
						Price price = priceRepository.findByType(seatObject.getType()).get();
						Ticket ticket = new Ticket(TicketState.RESERVED,user,show,price,seatObject, bookingId);
						tickets.add(ticket);
					} catch(NoSuchElementException e) {
						return new ResponseEntity<Object>(new SeatNotFoundException(seat).getMessage(),HttpStatus.NOT_FOUND);
					}
				}
				
				if(snackIDs != null && (!snackIDs.isEmpty())) {
					for(UUID snack : snackIDs) {
						try {
							Snack snackObject = snackRepository.findById(snack).get();
							snacks.add(snackObject);
						} catch(NoSuchElementException e) {
							return new ResponseEntity<Object>(new SnackNotFoundException(snack).getMessage(),HttpStatus.NOT_FOUND);
						}
					}
				}
				
				
				
				Booking booking = new Booking(bookingId, bookingObject.bookingDate, tickets, snacks, show, user , bookingObject.state);
				byte[] qrCode = qrCodeGenerator.generateQRCode("https://kino-frontend.vercel.app/info/"+booking.getId());
				booking.setQrCode(qrCode);
				
				ticketRepository.saveAll(tickets);

				emailService.sendMailBooking(
						user.getEmail(),
						"Buchung bestätigt!",
						new EmailVariablesObject(user.getUserName(), user.getFirstName(), user.getName(), "", "", show.getMovie().getTitle(), show.getShowDate().getDay()+"."+show.getShowDate().getMonth()+"."+show.getShowDate().getYear(), show.getStartTime().toString().substring(0,5), show.getCinemaRoom().getRoomName(), "", ""),
						"BookingConfirmation.html",
						qrCode,
						tickets,
						snacks
				);
				
				return new ResponseEntity<Object>(bookingRepositroy.save(booking), HttpStatus.CREATED);
			} catch(Exception e) {
				seatService.freeSeats(seatIDs, bookingObject.showID);
				ticketRepository.deleteAll(tickets);
				
				return new ResponseEntity<Object>(e.getMessage(),HttpStatus.CONFLICT);
			}
			
			
		} else {
			return new ResponseEntity<Object>(HttpStatus.CONFLICT);
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
	
	@PutMapping("/{id}/changeStatus")
	public ResponseEntity<Object> changeToPaid(@RequestBody BookingConfigurationObject bookingObject, @PathVariable UUID id){
		
		ArrayList<UUID> seatsToChange = new ArrayList<>();
		try {
			Booking booking = bookingRepositroy.findById(id).get();
			if(booking.getState() != bookingObject.state) {
				if(bookingObject.state == BookingState.Canceled) {
					List<Ticket> bookings =  booking.getTickets();
					for(Ticket ticket:bookings) {
						Seat seat = ticket.getSeat();
						seatsToChange.add(seat.getId());
						
					}
					
					seatService.freeSeats(seatsToChange, bookingObject.showID);
				}
				booking.setState(bookingObject.state);
				return new ResponseEntity<Object>(bookingRepositroy.save(booking), HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);
			}
		} catch(NoSuchElementException e) {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		} 
		
	}
	
	@GetMapping("/{id}/tickets")
	public ResponseEntity<Object> getTicketsForBooking(@PathVariable UUID id){
		List<Ticket> ticketSearch = ticketRepository.findAllByBookingID(id);
		return new ResponseEntity<Object>(ticketSearch, HttpStatus.OK);
	}

}
