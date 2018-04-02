package com.revature.hydra.batch;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
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

import com.revature.beans.BatchLocation;
import com.revature.hydra.batch.application.BatchRepositoryServiceApplication;
import com.revature.hydra.batch.data.BatchLocationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BatchRepositoryServiceApplication.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BatchLocationControllerTest {
	
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
	private BatchLocationRepository batchLocationRepository;
	
	private BatchLocation testBatchLocation;

	/**
	 * Create a test batch location in table to test on
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		this.testBatchLocation = new BatchLocation();
		this.testBatchLocation = this.batchLocationRepository.save(this.testBatchLocation);
	}

	/**
	 * Remove test batch location so that it doesn't cause problems with repeated runs 
	 * of the test and isn't left in database for production
	 */
	@After
	public void tearDown() {
		int testId = this.testBatchLocation.getBatchLocationId();
		if (this.batchLocationRepository.findOne(testId) != null) {
			this.batchLocationRepository.delete(testId);
		}
	}
	
	/**
	 * Test receiving a batch location by id
	 * @throws Exception
	 */
	@Test
	public void test1OneBatchLocationById() throws Exception {
		this.mockMvc.perform(get("/one/batchlocation/byid/" + this.testBatchLocation.getBatchLocationId()))
					.andExpect(status().isOk())
					.andExpect(content().contentType(this.mediaTypeJson))
					.andExpect(jsonPath("$.batchLocationName", CoreMatchers.is(this.testBatchLocation.getBatchLocationName())));
	}
	
	/**
	 * Test receiving all batch locations
	 * @throws Exception
	 */
	@Test
	public void test2AllBatchLocation() throws Exception {
		this.mockMvc.perform(get("/all/batchlocation"))
					.andExpect(status().isOk())
					.andExpect(content().contentType(this.mediaTypeJson))
					.andExpect(jsonPath("$[*].batchLocationId", Matchers.hasItem(this.testBatchLocation.getBatchLocationId())));
	}

	/**
	 * Used to convert a java object into a json
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	protected String json(Object obj) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(obj, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

}
