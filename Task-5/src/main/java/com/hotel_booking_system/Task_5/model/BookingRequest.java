package com.hotel_booking_system.Task_5.model;

import java.time.LocalDate;

public class BookingRequest {
	
	private Long roomId;
	private LocalDate checkIn;
	private LocalDate checkOut;

	public BookingRequest() {}
	
	public BookingRequest(Long roomId, LocalDate checkIn, LocalDate checkOut) {
		super();
		this.roomId = roomId;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}
}
