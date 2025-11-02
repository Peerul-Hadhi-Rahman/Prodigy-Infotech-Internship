package com.hotel_booking_system.Task_5.Controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hotel_booking_system.Task_5.Repository.BookingRepository;
import com.hotel_booking_system.Task_5.Repository.RoomRepository;
import com.hotel_booking_system.Task_5.Repository.UserRepository;
import com.hotel_booking_system.Task_5.model.BookingRequest;
import com.hotel_booking_system.Task_5.model.Booking;
import com.hotel_booking_system.Task_5.model.Room;
import com.hotel_booking_system.Task_5.model.User;

@RestController
@RequestMapping("/bookings")
public class BookingController {
	
	@Autowired
	private BookingRepository bookingRepo;
	
	@Autowired
	private RoomRepository roomRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@PostMapping
	public ResponseEntity<?> bookRoom(@RequestBody BookingRequest request, Principal principal) {
		User user = userRepo.findByEmail(principal.getName()).orElseThrow();
		Room room = roomRepo.findById(request.getRoomId()).orElseThrow();
		
		if(room.getOwner().getId().equals(user.getId())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot book your own room");
		}
		Booking booking = new Booking();
		booking.setUser(user);
		booking.setRoom(room);
		booking.setCheckIn(request.getCheckIn());
		booking.setCheckOut(request.getCheckOut());
	
		bookingRepo.save(booking);
		return ResponseEntity.ok("Room booked");
		}
	
	@GetMapping("/my")
	public ResponseEntity<?> myBookings(Principal principal) {
		User user = userRepo.findByEmail(principal.getName()).orElseThrow();
		return ResponseEntity.ok(bookingRepo.findByUserId(user.getId()));
	}
}
