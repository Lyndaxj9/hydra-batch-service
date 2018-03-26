package com.revature.hydra.batch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.BatchLocation;
import com.revature.hydra.batch.service.BatchLocationService;

/**
 * Service request for Batch information
 * @author Omowumi
 *
 */
@RestController
@CrossOrigin
@EnableAutoConfiguration
public class BatchLocationController {
	private BatchLocationService batchLocationService;
	
	@Autowired
	public void setBatchService(BatchLocationService batchLocationService) {
		this.batchLocationService = batchLocationService;
	}
	
	/**
	 * Retrieves a batch by id
	 * @param id
	 * @return ResponseEntity<Batch>
	 */
	@RequestMapping(value = "/one/batchlocation/byid/{id}", method = RequestMethod.GET)
	public ResponseEntity<BatchLocation> getBatchLocationById(@PathVariable Integer id) {
		return new ResponseEntity<>(batchLocationService.getBatchLocationById(id), HttpStatus.OK);
	}
}
