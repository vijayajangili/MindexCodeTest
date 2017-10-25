package com.codingtest.springbootrest;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.codingtest.springbootrest.controller.ItemController;
import com.codingtest.springbootrest.domain.Item;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ItemControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ItemController itemController;

	@Test
	public void testFindById() throws Exception {

		String dateStr = "2016-10-25 11:00:00";
		SimpleDateFormat sdf = new SimpleDateFormat(
			    "yyyy-MM-dd HH:mm:ss");
		Date dueDate = sdf.parse(dateStr);
		Item testItem = new Item((long) 1, "JAVA Book", "Describes Java Language",dueDate);
		given(itemController.findById(testItem.getId())).willReturn(new ResponseEntity<Item>(testItem, HttpStatus.OK));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/items/1").accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andReturn();
	}

}
