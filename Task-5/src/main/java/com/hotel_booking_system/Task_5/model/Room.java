package com.hotel_booking_system.Task_5.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rooms")
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String description;
	private int capacity;
	private double price;
	
	private LocalDate availableFrom;
	private LocalDate availableTo;
	
	@ManyToOne
	@JsonIgnoreProperties({"email", "password", "role"})
	private User owner;

	public Room() {}

	public Room(Long id, String title, String description, int capacity, double price, LocalDate availableFrom,
			LocalDate availableTo, User owner) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.capacity = capacity;
		this.price = price;
		this.availableFrom = availableFrom;
		this.availableTo = availableTo;
		this.owner = owner;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getAvailableFrom() {
		return availableFrom;
	}

	public void setAvailableFrom(LocalDate availableFrom) {
		this.availableFrom = availableFrom;
	}

	public LocalDate getAvailableTo() {
		return availableTo;
	}

	public void setAvailableTo(LocalDate availableTo) {
		this.availableTo = availableTo;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}
