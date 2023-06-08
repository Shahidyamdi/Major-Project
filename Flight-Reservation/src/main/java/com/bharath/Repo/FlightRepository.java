package com.bharath.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bharath.entities.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

	

}
