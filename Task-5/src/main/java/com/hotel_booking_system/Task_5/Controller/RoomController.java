package com.hotel_booking_system.Task_5.Controller;

import java.security.Principal;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hotel_booking_system.Task_5.Repository.RoomRepository;
import com.hotel_booking_system.Task_5.Repository.UserRepository;
import com.hotel_booking_system.Task_5.model.Room;
import com.hotel_booking_system.Task_5.model.User;

@RestController
@RequestMapping("/rooms")
public class RoomController {
	
	@Autowired
	private RoomRepository roomRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@PostMapping
	public ResponseEntity<?> createRoom(@RequestBody Room room, Principal principal) {
		User user = userRepo.findByEmail(principal.getName()).orElseThrow();
		room.setOwner(user);
		roomRepo.save(room);
		return ResponseEntity.ok("Room created");
		}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateRoom(@PathVariable Long id, @RequestBody Room updated, Principal principal) {
		Room room = roomRepo.findById(id).orElseThrow();
		if(!room.getOwner().getEmail().equals(principal.getName())) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not your room");
		}
		room.setTitle(updated.getTitle());
		room.setDescription(updated.getDescription());
		room.setPrice(updated.getPrice());
		room.setCapacity(updated.getCapacity());
		room.setAvailableFrom(updated.getAvailableFrom());
		room.setAvailableTo(updated.getAvailableTo());
		roomRepo.save(room);
		return ResponseEntity.ok("Room updated");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRoom(@PathVariable Long id, Principal principal) {
		Room room = roomRepo.findById(id).orElseThrow();
		if(!room.getOwner().getEmail().equals(principal.getName())) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not your room");
		}
		roomRepo.delete(room);
		return ResponseEntity.ok("Room deleted");
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> searchRooms(@RequestParam LocalDate from, @RequestParam LocalDate to) {
		return ResponseEntity.ok(roomRepo.findAvailableRooms(from, to));
	}
}
