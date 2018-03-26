package com.revature.hydra.batch;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.beans.BatchLocation;
import com.revature.hydra.batch.application.BatchRepositoryServiceApplication;
import com.revature.hydra.batch.data.BatchLocationRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = BatchRepositoryServiceApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
	
	@Test
	public void test2Update() {
		log.info("test2Update");
		String changeName = "changeNameTest";
		testBatchLocation.setBatchLocationName(changeName);
		BatchLocation updated = batchLocationRepository.save(testBatchLocation);
		Assert.assertEquals(changeName, updated.getBatchLocationName());
	}
	
	@Test
	public void test3Delete() {
		log.info("test3Delete");
		batchLocationRepository.delete(testId);
		Assert.assertNull(batchLocationRepository.findOne(testId));
	}

}
