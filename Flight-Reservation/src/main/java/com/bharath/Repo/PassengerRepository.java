package com.bharath.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bharath.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
