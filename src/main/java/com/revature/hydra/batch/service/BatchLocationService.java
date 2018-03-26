package com.revature.hydra.batch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.BatchLocation;
import com.revature.hydra.batch.data.BatchLocationRepository;

/**
 * Batch Location Service
 * 
 * Batch Location services implementation of communication with BatchLocationRepository
 * @author Omowumi
 *
 */
@Service
public class BatchLocationService {
	@Autowired
	private BatchLocationRepository batchLocationRepository;
	
	/**
	 * Get batch by the id
	 * @param batchId
	 * @return Batch
	 */
	public BatchLocation getBatchLocationById(Integer batchLocationId) {
		return batchLocationRepository.findOne(batchLocationId);
	}
}
