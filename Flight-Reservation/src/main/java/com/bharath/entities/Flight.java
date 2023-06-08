package com.bharath.entities;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Flight extends AbstractEntity{
	
	private String flightNumber;
	private String operatingAirlines;
	private String departureCity;
	private String arrivalCity;
	private Date dateOfDeparture;
	private Timestamp estimatedDepartureTime;


	
	

}
