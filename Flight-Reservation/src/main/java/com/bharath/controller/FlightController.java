package com.bharath.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bharath.Repo.FlightRepository;
import com.bharath.entities.Flight;

@Controller
public class FlightController {

	@Autowired
	FlightRepository flightRepository;

	@RequestMapping("/findFlights")
	public String findFlight(@RequestParam("from") String from, @RequestParam("to") String to,
			@RequestParam("dateOfDeparture") @DateTimeFormat(pattern = "DD-MM-YYYY") Date dateOfDeparture,
			ModelMap modelMap) {
		List<Flight> flights = flightRepository.findFlights(from, to, dateOfDeparture);
		modelMap.addAttribute("flights", flights);
		return "displayFlights";
	}

}
