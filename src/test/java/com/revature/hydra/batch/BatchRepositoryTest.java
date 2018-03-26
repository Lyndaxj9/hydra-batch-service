package com.revature.hydra.batch;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.hydra.batch.application.BatchRepositoryServiceApplication;
import com.revature.hydra.batch.data.BatchRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BatchRepositoryServiceApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BatchRepositoryTest {
	private static final Logger log = Logger.getLogger(BatchRepositoryTest.class);
	
	@Autowired
	BatchRepository batchRepository;
	
	

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		log.info("test");
	}

}
