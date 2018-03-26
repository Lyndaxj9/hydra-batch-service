package com.revature.hydra.batch.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Batch;

/**
 * BatchRepository Data Access Object with various methods to communicate with Batch Table
 * @author Omowumi
 *
 */
@Repository
public interface BatchRepository extends JpaRepository<Batch, Integer> {
	/**
	 * Find all batches
	 * 
	 * @return List<Batch>
	 */
	List<Batch> findAll();
	
	/**
	 * Find a batch by batch name
	 * @param batchName
	 * @return Batch
	 */
	Batch findOneByBatchName(String batchName);
	
	/**
	 * Find a batch by batch id
	 * @param batchId
	 * @return Batch
	 */
	Batch findOne(Integer batchId);
	
	/**
	 * Find all batches sorted by start date
	 * @return List<Batch>
	 */
	List<Batch> findAllByOrderByBatchStartDateAsc();
}
