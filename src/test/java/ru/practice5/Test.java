package ru.practice5;

import ru.practice5.service.getMdm;
import ru.practice5.service.InstanceEngine;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@SpringBootTest( classes = {Run.class})
@AutoConfigureMockMvc
@TestPropertySource("/app.yml")
@Testcontainers
@Transactional
class Test {
	@Container
	private static PostgreSQLContainer<?> connect = new PostgreSQLContainer<>("postgres:latest")
			.withDatabaseName("test")
			.withUsername("sa")
			.withPassword("sa")
			.withInitScript("database.sql");
	@DynamicPropertySource
	public static void properties(DynamicPropertyRegistry registry)
	{	registry.add("spring.datasource.url", connect::getJdbcUrl);
		registry.add("spring.datasource.username", connect::getUsername);
		registry.add("spring.datasource.password", connect::getPassword);
	}
	@Mock
	getMdm clientMdm;
	@InjectMocks
    InstanceEngine instanceService;
	@Autowired
	MockMvc mvc;
	private String getJsonFile(String path) throws IOException {File file = ResourceUtils.getFile("classpath:" + path);
		                                                                   return new String(Files.readAllBytes(file.toPath()));
	                                                                       }
	@org.junit.jupiter.api.Test
	void createInstance1() throws Exception {
		String json = getJsonFile("InstanceRequest1.json");
		this.mvc.perform(post("/corporate-settlement-instance/create")
					.contentType(MediaType.APPLICATION_JSON)
					.content(json))
					.andDo(print())
					.andExpect(status().isBadRequest());
	}
	@org.junit.jupiter.api.Test
	void createInstance2() throws Exception
	{	String reqOK = getJsonFile("InstanceRequest2.json");
		this.mvc.perform(post("/corporate-settlement-instance/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(reqOK))
				        .andDo(print())
				        .andExpect(status().isOk());
	}
	@org.junit.jupiter.api.Test
	public void createAccFault() throws Exception {
		this.mvc.perform(post("/corporate-settlement-account/create")
					    .param("instanceId", "1")
					    .param("registryTypeCode", "03.012.002_47533_ComSoLd")
					    .param("accountType", "Клиентский")
					    .param("currencyCode", "800")
					    .param("branchCode", "0022")
					    .param("priorityCode", "00")
					    .param("mdmCode", "15")
					    .param("clientCode", "123456"))
					    .andExpect(status().isBadRequest());
	}
}
