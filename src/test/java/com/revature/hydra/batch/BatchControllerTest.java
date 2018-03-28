package com.revature.hydra.batch;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.revature.beans.Batch;
import com.revature.hydra.batch.application.BatchRepositoryServiceApplication;
import com.revature.hydra.batch.data.BatchRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BatchRepositoryServiceApplication.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BatchControllerTest {
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {
		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
					.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
					.findAny()
					.orElse(null);
		
		Assert.assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
	}
	
	private final String mediaTypeJson = MediaType.APPLICATION_JSON_UTF8_VALUE;
	
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	
	private MockMvc mockMvc;
	
	@Autowired
	private BatchRepository batchRepository;
	
	private Batch testBatch;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		this.testBatch = new Batch();
		this.testBatch.setBatchName("testBatchName");
		this.testBatch.setBatchLocationId(1);
		this.testBatch.setBatchStartDate(new Timestamp(1494259200323L));
		this.testBatch.setBatchEndDate(new Timestamp(1495209600323L));
		this.testBatch.setCurriculumId(5);
		this.testBatch = this.batchRepository.save(this.testBatch);
	}

	@After
	public void tearDown() throws Exception {
		int testId = this.testBatch.getBatchId();
		if (this.batchRepository.findOne(testId) != null) {
			this.batchRepository.delete(testId);
		}
	}

	@Test
	public void test1OneByName() throws Exception {
		this.mockMvc.perform(get("/one/batch/byname/" + this.testBatch.getBatchName()))
					.andExpect(status().isOk())
					.andExpect(content().contentType(this.mediaTypeJson))
					.andExpect(jsonPath("$.batchName", CoreMatchers.is(this.testBatch.getBatchName())))
					.andExpect(jsonPath("$.curriculumId", CoreMatchers.is(this.testBatch.getCurriculumId())))
					.andExpect(jsonPath("$.batchLocationId", CoreMatchers.is(this.testBatch.getBatchLocationId())));
	}
	
	@Test
	public void test2OneById() throws Exception {
		this.mockMvc.perform(get("/one/batch/byid/" + this.testBatch.getBatchId()))
					.andExpect(status().isOk())
					.andExpect(content().contentType(this.mediaTypeJson))
					.andExpect(jsonPath("$.batchId", CoreMatchers.is(this.testBatch.getBatchId())))
					.andExpect(jsonPath("$.batchName", CoreMatchers.is(this.testBatch.getBatchName())))
					.andExpect(jsonPath("$.curriculumId", CoreMatchers.is(this.testBatch.getCurriculumId())))
					.andExpect(jsonPath("$.batchLocationId", CoreMatchers.is(this.testBatch.getBatchLocationId())));
	}
	
	@Test
	public void test3OneBatchCurriculum() throws Exception {
		this.mockMvc.perform(get("/one/batch/curriculum/" + this.testBatch.getBatchId()))
					.andExpect(status().isOk())
					.andExpect(content().contentType(this.mediaTypeJson))
					.andExpect(jsonPath("$", CoreMatchers.is(this.testBatch.getCurriculumId())));
	}
	
	@Test
	public void test4AllBatchOrdered() throws Exception {
		this.mockMvc.perform(get("/all/batch/ordered"))
					.andExpect(status().isOk())
					.andExpect(content().contentType(this.mediaTypeJson));
	}
	
	@Test
	public void test5AllBatchMapped() throws Exception {
		this.mockMvc.perform(get("/all/batch/mapped"))
					.andExpect(status().isOk())
					.andExpect(content().contentType(this.mediaTypeJson))
					.andExpect(jsonPath("$.20.batchId", CoreMatchers.is(20)));
	}
	
	@Test
	public void test6AllBatchSet() throws Exception {
		this.mockMvc.perform(get("/all/batch/set"))
					.andExpect(status().isOk())
					.andExpect(content().contentType(this.mediaTypeJson))
					.andExpect(jsonPath("$[*].batchId", Matchers.hasItem(this.testBatch.getBatchId())));
	}
	
	@Test
	public void test7AllBatchBtwDates() throws Exception {
		Long fromDate = 1497283200001L;
		Long toDate = 1503676800001L;
		this.mockMvc.perform(get("/all/batch/btw/" + fromDate + "/" + toDate))
					.andExpect(status().isOk())
					.andExpect(content().contentType(this.mediaTypeJson))
					.andExpect(jsonPath("$[0].batchEndDate", Matchers.greaterThanOrEqualTo(fromDate)));
					//.andExpect(jsonPath("$[-1:].batchEndDate", Matchers.lessThanOrEqualTo(toDate)));
	}
	
	@Test
	public void test8AddBatch() throws Exception {
		Batch addBatch = new Batch();
		addBatch.setBatchName("testAddBatchName");
		addBatch.setBatchLocationId(1);
		addBatch.setBatchStartDate(new Timestamp(1494259200324L));
		addBatch.setBatchEndDate(new Timestamp(1495209600324L));
		addBatch.setCurriculumId(1);
		this.mockMvc.perform(post("/add/batch")
					.content(this.json(addBatch))
					.contentType(this.mediaTypeJson))
					.andExpect(status().isCreated());
	}
	
	@Test
	public void test9UpdateBatch() throws Exception {
		this.testBatch.setBatchName("updateBatchName");
		this.mockMvc.perform(put("/update/batch/" + this.testBatch.getBatchId())
					.content(this.json(this.testBatch))
					.contentType(this.mediaTypeJson))
					.andExpect(status().isOk());
	}
	
	@Test
	public void test10DeleteBatch() throws Exception {
		this.mockMvc.perform(delete("/delete/batch/" + this.testBatch.getBatchId()))
					.andExpect(status().isOk());
	}
	
	protected String json(Object obj) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(obj, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

}
