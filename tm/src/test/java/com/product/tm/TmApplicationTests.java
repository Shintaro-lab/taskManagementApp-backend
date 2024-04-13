package com.product.tm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.tm.entity.TaskCard;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TmApplicationTests {

	@Autowired
    private MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		String masterFilePath = "./src/test/resources/tmApplicationTestsMaster.json";
		String databaseFile = "./src/test/resources/tmApplicationTestsDatabase.json";

		ObjectMapper mapperInput = new ObjectMapper();
		ArrayList<TaskCard> masterJson = mapperInput.readValue(new File(masterFilePath),new TypeReference <ArrayList<TaskCard>>() {});

		ObjectMapper mapperDatabase = new ObjectMapper();
		mapperDatabase.writeValue(new File(databaseFile), masterJson);
	}

	@Test
	@DisplayName("Test001")
	void データ取得用URLをたたくとjson形式のデータが返ってくる() throws Exception {

		String expectedFilePath = "./src/test/resources/tmApplicationTestsTest001Expected.json";

		ObjectMapper mapperExpected = new ObjectMapper();
		ArrayList<TaskCard> expectedJson = mapperExpected.readValue(new File(expectedFilePath),new TypeReference <ArrayList<TaskCard>>() {});
		String expected = mapperExpected.writeValueAsString(expectedJson);

		mockMvc.perform(MockMvcRequestBuilders.get("/tm/getLatestData"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().json(expected));
	}

	@Test
	@DisplayName("Test002")
	void データ更新用URLをたたくとjson形式のデータが書き込まれる() throws Exception {

		String expectedFilePath = "./src/test/resources/tmApplicationTestsTest002Expected.json";
		String inputFilePath = "./src/test/resources/tmApplicationTestsTest002Input.json";
		String databaseFile = "./src/test/resources/tmApplicationTestsDatabase.json";

		ObjectMapper mapperInput = new ObjectMapper();
		ArrayList<TaskCard> inputJson = mapperInput.readValue(new File(inputFilePath),new TypeReference <ArrayList<TaskCard>>() {});
		String input = mapperInput.writeValueAsString(inputJson);

		ObjectMapper mapperExpected = new ObjectMapper();
		ArrayList<TaskCard> expectedJson = mapperExpected.readValue(new File(expectedFilePath),new TypeReference <ArrayList<TaskCard>>() {});
		String expected = mapperExpected.writeValueAsString(expectedJson);

		mockMvc.perform(MockMvcRequestBuilders.post("/tm/updateData")
				.contentType("application/json")
				.content(input))
			.andExpect(MockMvcResultMatchers.status().isOk());

		ObjectMapper mapperDatabase = new ObjectMapper();
		ArrayList<TaskCard> actualJson = mapperDatabase.readValue(new File(databaseFile),new TypeReference <ArrayList<TaskCard>>() {});
		String actual = mapperDatabase.writeValueAsString(actualJson);

		assertEquals(expected, actual);
	
	}

}
