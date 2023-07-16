package br.com.banco;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class TransferenciaTest {

	@LocalServerPort
	private int port;

	private String baseUrl = "http://localhost";

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		baseUrl = baseUrl.concat(":").concat(port + "").concat("/transferencias").concat("?accountId=1");
	}

	@Test
	@Order(1)
	public void testGetAllTransactionByAccoutId() throws Exception {
		this.mockMvc.perform(get(this.baseUrl).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is(HttpStatus.OK.value()))
		.andExpect(jsonPath("$.length()").value(3))
		.andExpect(jsonPath("$[0].conta.name").value("Fulano"))
		.andExpect(jsonPath("$[0].conta.id").value(1))
		.andExpect(jsonPath("$[1].conta.id").value(1))
		.andExpect(jsonPath("$[2].conta.id").value(1));
	}
	
	@Test
	@Order(2)
	public void testGetAllTransactionByAccoutIdAndOperatorName() throws Exception {
		this.mockMvc.perform(get(this.baseUrl.concat("&operatorName=Beltrano")).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is(HttpStatus.OK.value()))
		.andExpect(jsonPath("$[0].conta.name").value("Fulano"))
		.andExpect(jsonPath("$[0].conta.id").value(1))
		.andExpect(jsonPath("$[0].transactionOperatorName").value("Beltrano"));
	}
}
