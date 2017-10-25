package com.codingtest.springbootrest;

import java.text.SimpleDateFormat;

import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.codingtest.springbootrest.domain.Item;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringbootrestApplication.class)

@AutoConfigureMockMvc
public class SpringbootrestApplicationTests {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void contextLoads() throws Exception {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		String dateStr = "2016-10-25 15:00:00"; // converting to UTC time
		String expected = "{\"id\":1,\"title\":\"JAVA\",\"description\":\"JAVA language\",\"dueDate\":\"" + dateStr
				+ "\"}";
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/items/1/"), HttpMethod.GET, entity,
				String.class);
		JSONAssert.assertEquals(expected, response.getBody(), false);

	}

	@Test
	public void addItem() throws Exception {

		String dateStr = "2016-10-25 11:00:00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");

		Date dueDate = sdf.parse(dateStr);

		Item newItem = new Item((long) 1, "JAVA", "JAVA language", dueDate);

		HttpEntity<Item> entity = new HttpEntity<Item>(newItem, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/items/load"), HttpMethod.POST,
				entity, String.class);

	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
