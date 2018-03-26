package com.revature.hydra.batch.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	/**
	 * Retrieves a batch by name
	 * @param name
	 * @return ResponseEntity<Batch>
	 */
	@RequestMapping(value = "/one/batch/byname/{name}", method = RequestMethod.GET)
	public ResponseEntity<Batch> getBatchByName(@PathVariable String name) {
		return new ResponseEntity<>(batchService.getBatchByName(name), HttpStatus.OK);
	}
	
	/**
	 * Retrieves a batch by id
	 * @param id
	 * @return ResponseEntity<Batch>
	 */
	@RequestMapping(value = "/one/batch/byid/{id}", method = RequestMethod.GET)
	public ResponseEntity<Batch> getBatchById(@PathVariable Integer id) {
		return new ResponseEntity<>(batchService.getBatchById(id), HttpStatus.OK);
	}
	
	/**
	 * Retrieves all batches in order of start date
	 * @return ResponseEntity<List<Batch>>
	 */
	@RequestMapping(value = "/all/batch/ordered", method = RequestMethod.GET)
	public ResponseEntity<List<Batch>> getAllBatchesOrdered() {
		return new ResponseEntity<>(batchService.getBatchesOrderedByDate(), HttpStatus.OK);
	}
	
	/**
	 * Retrieves all batches in a map
	 * @return ResponseEntity<Map<Integer, Batch>>
	 */
	@RequestMapping(value = "/all/batch/mapped", method = RequestMethod.GET)
	public ResponseEntity<Map<Integer, Batch>> getAllBatchesMapped() {
		return new ResponseEntity<>(batchService.getBatchesMapped(), HttpStatus.OK);
	}
	
	/**
	 * Retrieves all batches in a set
	 * @return ResponseEntity<HashSet<Batch>>
	 */
	@RequestMapping(value = "/all/batch/set", method = RequestMethod.GET)
	public ResponseEntity<HashSet<Batch>> getAllBatchesSet() {
		return new ResponseEntity<>(batchService.getBatchesSet(), HttpStatus.OK);
	}
	
	/**
	 * Create a new batch
	 * @param batch
	 * @return ResponseEntity<Void>
	 */
	@RequestMapping(value = "/add/batch", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> addBatch(@RequestBody Batch batch) {
		batchService.addBatch(batch);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * Update existing batch info
	 * @param id
	 * @param batch
	 * @return ResponseEntity<Void>
	 */
	@RequestMapping(value = "/update/batch/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateBatchInfo(@PathVariable Integer id, @RequestBody Batch batch) {
		batchService.updateBatch(id, batch);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * Delete a batch by id
	 * @param id
	 * @return ResponseEntity<Void>
	 */
	@RequestMapping(value = "/delete/batch/{id}", method = RequestMethod.DELETE) 
	public ResponseEntity<Void> deleteBatch(@PathVariable Integer id) {
		batchService.deleteBatch(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
