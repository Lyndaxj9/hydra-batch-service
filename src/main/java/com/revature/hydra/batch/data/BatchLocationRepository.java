package com.revature.hydra.batch.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.BatchLocation;

/**
 * BatchLocationRepository Data Access Object with various methods to communicate with Batch Location Table
 * @author Omowumi
 *
 */
@Repository
public interface BatchLocationRepository extends JpaRepository<BatchLocation, Integer> {
	/**
	 * Get a batch location by id
	 * @return BatchLocation
	 */
	BatchLocation findOne(Integer batchLocationId);
	
	/**
	 * Get a list of all batch locations
	 * @return List<BatchLocation>
	 */
	List<BatchLocation> findAll();
}
