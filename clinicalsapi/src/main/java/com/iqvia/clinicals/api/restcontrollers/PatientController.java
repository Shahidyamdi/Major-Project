package com.iqvia.clinicals.api.restcontrollers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iqvia.clinicals.api.model.ClinicalData;
import com.iqvia.clinicals.api.model.Patient;
import com.iqvia.clinicals.api.repository.PatientRepository;
import com.iqvia.clinicals.util.calculatebmi;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PatientController {

	private PatientRepository patientRepository;

	Map<String, String> filter = new HashMap<>();

	@Autowired
	PatientController(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	@RequestMapping(value = "/patients", method = RequestMethod.POST)
	public Patient savePatient(@RequestBody Patient patient) {
		System.out.println(patient.getFirstName());
		return patientRepository.save(patient);
	}

	@RequestMapping(value = "/patients/{id}", method = RequestMethod.GET)
	public Patient getPatient(@PathVariable("id") int id) {
		return patientRepository.findById(id).get();
	}

	@RequestMapping(value = "/patients", method = RequestMethod.GET)
	public List<Patient> getPatients() {
		return patientRepository.findAll();
	}

	@RequestMapping(value = "/patients/analyze/{id}", method = RequestMethod.GET)
	public Patient analyse(@PathVariable("id") int id) {
		Patient patient = patientRepository.findById(id).get();
		List<ClinicalData> clincialData = patient.getClinicalData();
		List<ClinicalData> duplicateclinicalData = new ArrayList<>(clincialData);
		for (ClinicalData eachEntry : duplicateclinicalData) {
			if (filter.containsKey(eachEntry.getComponentName())) {
				clincialData.remove(eachEntry);
				continue;

			} else {
				filter.put(eachEntry.getComponentName(), null);
			}

			calculatebmi.calculateBMI(duplicateclinicalData, eachEntry);
			filter.clear();

		}
		return patient;

	}
}