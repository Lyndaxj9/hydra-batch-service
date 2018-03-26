package com.revature.hydra.batch;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.beans.BatchLocation;
import com.revature.hydra.batch.data.BatchLocationRepository;

public class BatchLocationRepositoryTest {

private static final Logger log = Logger.getLogger(BatchLocationRepositoryTest.class);
	
	@Autowired
	BatchLocationRepository batchLocationRepository;
	
	BatchLocation testBatchLocation;
	
	Integer testId;

	@Before
	public void setUp() throws Exception {
		log.info("setUp");
		testBatchLocation = new BatchLocation();
		testBatchLocation.setBatchLocationName("testBatchLocation");
		testBatchLocation = batchLocationRepository.save(testBatchLocation);
		testId = testBatchLocation.getBatchLocationId();
	}

	@After
	public void tearDown() throws Exception {
		log.info("tearDown");
		if(batchLocationRepository.findOne(testId) != null) {
			batchLocationRepository.delete(testId);
		}
	}

	@Test
	public void test1FindOne() {
		log.info("test1FindOne");
		BatchLocation bl = batchLocationRepository.findOne(testId);
		Assert.assertEquals(testBatchLocation.getBatchLocationId(), bl.getBatchLocationId());
	}

}
