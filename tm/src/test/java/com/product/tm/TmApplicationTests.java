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
import com.product.tm.entity.Task;
import com.product.tm.entity.TaskCard;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TmApplicationTests {

	@Autowired
    private MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		String masterTaskFilePath = "./src/test/resources/tmApplicationTestsMasterTaskData.json";
		String masterTaskCardFilePath = "./src/test/resources/tmApplicationTestsMasterTaskCardData.json";
		String taskDatabaseFile = "./src/test/resources/tmApplicationTestsTaskData.json";
		String taskCardDatabaseFile = "./src/test/resources/tmApplicationTestsTaskCardData.json";

		ObjectMapper mapperInputForTask = new ObjectMapper();
		ArrayList<Task> masterJsonForTask = mapperInputForTask.readValue(new File(masterTaskFilePath),new TypeReference <ArrayList<Task>>() {});

		ObjectMapper mapperDatabaseForTask = new ObjectMapper();
		mapperDatabaseForTask.writeValue(new File(taskDatabaseFile), masterJsonForTask);

		ObjectMapper mapperInputForTaskCard = new ObjectMapper();
		ArrayList<TaskCard> masterJsonForTaskCard = mapperInputForTaskCard.readValue(new File(masterTaskCardFilePath),new TypeReference <ArrayList<TaskCard>>() {});

		ObjectMapper mapperDatabaseForTaskCard = new ObjectMapper();
		mapperDatabaseForTaskCard.writeValue(new File(taskCardDatabaseFile), masterJsonForTaskCard);
	}

	@Test
	@DisplayName("Test001")
	void タスクカード取得用URLをたたくとjson形式のデータが返ってくる() throws Exception {

		String expectedFilePath = "./src/test/resources/tmApplicationTestsTest001Expected.json";

		ObjectMapper mapperExpected = new ObjectMapper();
		ArrayList<TaskCard> expectedJson = mapperExpected.readValue(new File(expectedFilePath),new TypeReference <ArrayList<TaskCard>>() {});
		String expected = mapperExpected.writeValueAsString(expectedJson);

		mockMvc.perform(MockMvcRequestBuilders.get("/tm/getLatestTaskCardData"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().json(expected));
	}

	@Test
	@DisplayName("Test002")
	void タスクカード更新用URLをたたくとjson形式のデータが書き込まれる() throws Exception {

		String expectedFilePath = "./src/test/resources/tmApplicationTestsTest002Expected.json";
		String inputFilePath = "./src/test/resources/tmApplicationTestsTest002Input.json";
		String databaseFile = "./src/test/resources/tmApplicationTestsTaskCardData.json";

		ObjectMapper mapperInput = new ObjectMapper();
		ArrayList<TaskCard> inputJson = mapperInput.readValue(new File(inputFilePath),new TypeReference <ArrayList<TaskCard>>() {});
		String input = mapperInput.writeValueAsString(inputJson);

		ObjectMapper mapperExpected = new ObjectMapper();
		ArrayList<TaskCard> expectedJson = mapperExpected.readValue(new File(expectedFilePath),new TypeReference <ArrayList<TaskCard>>() {});
		String expected = mapperExpected.writeValueAsString(expectedJson);

		mockMvc.perform(MockMvcRequestBuilders.post("/tm/updateTaskCardData")
				.contentType("application/json")
				.content(input))
			.andExpect(MockMvcResultMatchers.status().isOk());

		ObjectMapper mapperDatabase = new ObjectMapper();
		ArrayList<TaskCard> actualJson = mapperDatabase.readValue(new File(databaseFile),new TypeReference <ArrayList<TaskCard>>() {});
		String actual = mapperDatabase.writeValueAsString(actualJson);

		assertEquals(expected, actual);
	
	}

	@Test
	@DisplayName("Test003")
	void タスク取得用URLをたたくとjson形式のデータが返ってくる() throws Exception {

		String expectedFilePath = "./src/test/resources/tmApplicationTestsTest003Expected.json";

		ObjectMapper mapperExpected = new ObjectMapper();
		ArrayList<Task> expectedJson = mapperExpected.readValue(new File(expectedFilePath),new TypeReference <ArrayList<Task>>() {});
		String expected = mapperExpected.writeValueAsString(expectedJson);

		mockMvc.perform(MockMvcRequestBuilders.get("/tm/getLatestTaskData"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().json(expected));
	}

	@Test
	@DisplayName("Test004")
	void タスク更新用URLをたたくとjson形式のデータが書き込まれる() throws Exception {

		String expectedFilePath = "./src/test/resources/tmApplicationTestsTest004Expected.json";
		String inputFilePath = "./src/test/resources/tmApplicationTestsTest004Input.json";
		String databaseFile = "./src/test/resources/tmApplicationTestsTaskData.json";

		ObjectMapper mapperInput = new ObjectMapper();
		ArrayList<Task> inputJson = mapperInput.readValue(new File(inputFilePath),new TypeReference <ArrayList<Task>>() {});
		String input = mapperInput.writeValueAsString(inputJson);

		ObjectMapper mapperExpected = new ObjectMapper();
		ArrayList<Task> expectedJson = mapperExpected.readValue(new File(expectedFilePath),new TypeReference <ArrayList<Task>>() {});
		String expected = mapperExpected.writeValueAsString(expectedJson);

		mockMvc.perform(MockMvcRequestBuilders.post("/tm/updateTaskData")
				.contentType("application/json")
				.content(input))
			.andExpect(MockMvcResultMatchers.status().isOk());

		ObjectMapper mapperDatabase = new ObjectMapper();
		ArrayList<Task> actualJson = mapperDatabase.readValue(new File(databaseFile),new TypeReference <ArrayList<Task>>() {});
		String actual = mapperDatabase.writeValueAsString(actualJson);

		assertEquals(expected, actual);
	
	}

}
