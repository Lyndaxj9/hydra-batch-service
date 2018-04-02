package com.revature.hydra.batch;

import java.sql.Timestamp;
import java.util.List;

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

import com.revature.beans.Batch;
import com.revature.hydra.batch.application.BatchRepositoryServiceApplication;
import com.revature.hydra.batch.data.BatchRepository;

/**
 * Testing for the Batch Repository
 * @author Omowumi
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BatchRepositoryServiceApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BatchRepositoryTest {
	private static final Logger log = Logger.getLogger(BatchRepositoryTest.class);
	
	@Autowired
	BatchRepository batchRepository;
	
	Batch testBatch;
	
	Integer testId;
	
	/**
	 * Create a test batch in table to test on
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		log.info("setUp");
		testBatch = new Batch();
		testBatch.setBatchName("testBatch");
		testBatch.setBatchLocationId(5);
		testBatch.setBatchStartDate(new Timestamp(1494259200323L));
		testBatch.setBatchEndDate(new Timestamp(1495209600323L));
		testBatch.setCurriculumId(5);
		testBatch = batchRepository.save(testBatch);
		testId = testBatch.getBatchId();
	}

	/**
	 * Remove test batch so that it doesn't cause problems with repeated runs of the test and isn't left in database for production
	 */
	@After
	public void tearDown() {
		log.info("tearDown");
		if(batchRepository.findOne(testId) != null) {
			batchRepository.delete(testId);
		}
	}

	/**
	 * Test receiving all batches in a list
	 */
	@Test
	public void test1FindAll() {
		log.info("test1FindAll");
		List<Batch> batches = batchRepository.findAll();
		Assert.assertNotNull(batches);
	}
	
	/**
	 * Test receiving one batch by the batch name
	 */
	@Test
	public void test2FindOneByBatchName() {
		log.info("test2FindOneByBatchName");
		Batch b = batchRepository.findOneByBatchName(testBatch.getBatchName());
		Assert.assertEquals(testBatch.getBatchName(), b.getBatchName());
	}
	
	/**
	 * Test receiving one batch by the batch id
	 */
	@Test
	public void test3FindOne() {
		log.info("test3FindOne");
		Batch b = batchRepository.findOne(testId);
		Assert.assertEquals(testBatch.getBatchId(), b.getBatchId());
	}
	
	/**
	 * Test receiving batches with end date between the two dates passed in
	 */
	@Test
	public void test4FindAllByOrderByBatchStartDateAsc() {
		log.info("test4FindAllByOrderByBatchStartDateAsc");
		List<Batch> batches = batchRepository.findAllByOrderByBatchStartDateAsc();
		
		int i = 0;
		Batch first = batches.get(i);
		
		while(first.getBatchStartDate() == null) {
			i++;
			first = batches.get(i);
		}
		
		Batch last = batches.get(batches.size()-1);
		Assert.assertTrue(first.getBatchStartDate().getTime() < last.getBatchStartDate().getTime());
	}
	
	/**
	 * Test updating a batch
	 */
	@Test
	public void test5Update() {
		log.info("test5Update");
		String changeName = "changedBatchName";
		testBatch.setBatchName(changeName);
		Batch updated = batchRepository.save(testBatch);
		Assert.assertEquals(changeName, updated.getBatchName());
	}
	
	/**
	 * Test deleteing a batch
	 */
	@Test
	public void test6Delete() {
		log.info("test6Delete");
		batchRepository.delete(testId);
		Assert.assertNull(batchRepository.findOne(testId));
	}

}
