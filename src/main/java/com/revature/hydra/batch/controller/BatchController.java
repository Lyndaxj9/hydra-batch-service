package com.revature.hydra.batch.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Batch;
import com.revature.hydra.batch.service.BatchService;

/**
 * Service request for Batch information
 * @author Omowumi
 *
 */
@RestController
@CrossOrigin
@EnableAutoConfiguration
public class BatchController {
	private BatchService batchService;
	
	@Autowired
	public void setBatchService(BatchService batchService) {
		this.batchService = batchService;
	}
	
	@RequestMapping(value = "/one/batch/byname/{name}", method = RequestMethod.GET)
	public ResponseEntity<Batch> getBatchByName(@PathVariable String name) {
		return new ResponseEntity<>(batchService.getBatchByName(name), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/one/batch/byid/{id}", method = RequestMethod.GET)
	public ResponseEntity<Batch> getBatchByName(@PathVariable Integer id) {
		return new ResponseEntity<>(batchService.getBatchById(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/all/batch/ordered", method = RequestMethod.GET)
	public ResponseEntity<List<Batch>> getAllBatchesOrdered() {
		return new ResponseEntity<>(batchService.getBatchesOrderedByDate(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/all/batch/mapped", method = RequestMethod.GET)
	public ResponseEntity<Map<Integer, Batch>> getAllBatchesMapped() {
		return new ResponseEntity<>(batchService.getBatchesMapped(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/all/batch/set", method = RequestMethod.GET)
	public ResponseEntity<HashSet<Batch>> getAllBatchesSet() {
		return new ResponseEntity<>(batchService.getBatchesSet(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update/batch/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateBatchInfo() {
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
