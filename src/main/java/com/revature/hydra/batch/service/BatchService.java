package com.revature.hydra.batch.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Batch;
import com.revature.hydra.batch.data.BatchRepository;

/**
 * Batch Service
 * 
 * Batch services implementation of communication with BatchRepository
 * @author Omowumi
 *
 */
@Service
public class BatchService {
	@Autowired
	private BatchRepository batchRepository;
	
	/**
	 * Get batch by the name
	 * @param batchName
	 * @return Batch
	 */
	public Batch getBatchByName(String batchName) {
		return batchRepository.findOneByBatchName(batchName);
	}
	
	/**
	 * Get batch by the id
	 * @param batchId
	 * @return Batch
	 */
	public Batch getBatchById(Integer batchId) {
		return batchRepository.findOne(batchId);
	}
	
	/**
	 * Get all batches in a map
	 * @return Map<Integer, Batch>
	 */
	public Map<Integer, Batch> getBatchesMapped() {
		List<Batch> lb = batchRepository.findAll();
		Map<Integer, Batch> map = createMapping(lb);
		
		return map;
	}
	
	/**
	 * Get all batches in order by startdate
	 * @return List<Batch>
	 */
	public List<Batch> getBatchesOrderedByDate() {
		return batchRepository.findAllByOrderByBatchStartDateAsc();
	}
	
	/**
	 * Get all associate id's from a specific batch
	 * @param batchId
	 * @return HashSet<Integer>
	 */
	public HashSet<Integer> getAssociates(Integer batchId) {
		Batch b = batchRepository.findOne(batchId);
		return b.getAssociates();
	}
	
	/**
	 * Get all batches in a set
	 * @return HashSet<Batch>
	 */
	public HashSet<Batch> getBatchesSet() {
		List<Batch> lb = batchRepository.findAll();
		HashSet<Batch> hsb = new HashSet<Batch>(lb);
		return hsb;
	}
	
	/**
	 * Add a new batch
	 * @param batch
	 */
	public void addBatch(Batch batch) {
		batchRepository.save(batch);
	}
	
	/**
	 * Update batch information
	 * @param batchId
	 * @param batch
	 */
	public void updateBatch(Integer batchId, Batch batch) {
		batch.setBatchId(batchId);
		batchRepository.save(batch);
	}
	
	/**
	 * Delete a batch by batchId
	 * @param batchId
	 */
	public void deleteBatch(Integer batchId) {
		if(batchRepository.findOne(batchId) != null) {
			batchRepository.delete(batchId);
		}
	}
	
	/**
	 * Create a mapping from a list
	 * @param lb
	 * @return Map<Integer, Batch>
	 */
	private Map<Integer, Batch> createMapping(List<Batch> lb) {
		Map<Integer, Batch> map = new HashMap<>();

		if (lb != null) {
			for (Batch b : lb) {
				map.put(b.getBatchId(), b);
			}
		}

		return map;
	}
}
