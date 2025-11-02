package com.hotel_booking_system.Task_5.Repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.hotel_booking_system.Task_5.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long>{
	 @Query("SELECT r FROM Room r WHERE r.availableFrom <= :checkIn AND r.availableTo >= :checkOut")
	 List<Room> findAvailableRooms(@Param("checkIn") LocalDate checkIn, @Param("checkOut") LocalDate checkOut);
}
