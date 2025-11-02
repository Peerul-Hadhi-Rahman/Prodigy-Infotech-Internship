package com.hotel_booking_system.Task_5.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hotel_booking_system.Task_5.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	List<Booking> findByUserId(Long userId);
}
