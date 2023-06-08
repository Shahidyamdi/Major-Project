package com.bharath.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Reservation extends AbstractEntity{
	private Boolean checkedIn;
	private int numberOfBags;
	@OneToOne
	private Passenger passenger;
	@OneToOne
	private Flight flight;

	
}
