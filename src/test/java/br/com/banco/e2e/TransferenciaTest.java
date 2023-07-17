package br.com.banco.e2e;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
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
	
	private String baseUrlInvalidId;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		baseUrl = baseUrl.concat(":").concat(port + "").concat("/transferencias").concat("?accountId=1");
		baseUrlInvalidId = baseUrl.concat(":").concat(port + "").concat("/transferencias").concat("?accountId=q");
	}

	@Test
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
	public void testGetAllTransactionByAccoutIdAndOperatorName() throws Exception {
		this.mockMvc.perform(get(this.baseUrl.concat("&operatorName=Beltrano")).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is(HttpStatus.OK.value()))
		.andExpect(jsonPath("$.length()").value(1))
		.andExpect(jsonPath("$[0].conta.name").value("Fulano"))
		.andExpect(jsonPath("$[0].conta.id").value(1))
		.andExpect(jsonPath("$[0].transactionOperatorName").value("Beltrano"));
	}
	
	@Test
	public void testGetAllTransactionByAccoutIdAndDateRange() throws Exception {
	    this.mockMvc.perform(get(this.baseUrl.concat("&initialDate=2018-01-01&finalDate=2023-07-15")).contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().is(HttpStatus.OK.value()))
	        .andExpect(jsonPath("$.length()").value(3))
	        .andExpect(jsonPath("$[0].conta.name").value("Fulano"))
	        .andExpect(jsonPath("$[0].conta.id").value(1))
	        .andExpect(jsonPath("$[1].conta.id").value(1))
	        .andExpect(jsonPath("$[2].conta.id").value(1))
	        .andExpect(jsonPath("$[0].transferDate").value(Matchers.startsWith(("2019"))))
	        .andExpect(jsonPath("$[1].transferDate").value(Matchers.startsWith(("2019"))))
	        .andExpect(jsonPath("$[2].transferDate").value(Matchers.startsWith(("2020"))));
	}
	
	@Test
	public void testGetAllTransactionByAccoutIdOperatorNameAndDateRange() throws Exception {
	    this.mockMvc.perform(get(this.baseUrl.concat("&initialDate=2018-01-01&finalDate=2023-07-15&operatorName=Beltrano")).contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().is(HttpStatus.OK.value()))
	        .andExpect(jsonPath("$.length()").value(1))
	        .andExpect(jsonPath("$[0].conta.name").value("Fulano"))
	        .andExpect(jsonPath("$[0].conta.id").value(1))
	        .andExpect(jsonPath("$[0].transferDate").value(Matchers.startsWith(("2020"))));
	}
	
	@Test
	public void testGetAllTransactionByAccoutIdOperatorNameAndDateRange_whenPassFinalDateAfterInitial() throws Exception {
	    this.mockMvc.perform(get(this.baseUrl.concat("&initialDate=2023-07-15&finalDate=2018-01-01&operatorName=Beltrano")).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
        .andExpect(jsonPath("$.message").value("A data de fim não pode ser anterior à data de início."))
        .andExpect(jsonPath("$.error").value("Error in your request"));
	}
	
	@Test
	public void testGetAllTransactionByAccoutIdAndDateRange_whenPassFinalDateAfterInitial() throws Exception {
	    this.mockMvc.perform(get(this.baseUrl.concat("&initialDate=2023-07-15&finalDate=2018-01-01")).contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
	        .andExpect(jsonPath("$.message").value("A data de fim não pode ser anterior à data de início."))
	        .andExpect(jsonPath("$.error").value("Error in your request"));
	}
	
	@Test
	public void testGetAllTransactionByAccoutIdAndDateRange_whenDontSendInitialDate() throws Exception {
	    this.mockMvc.perform(get(this.baseUrl.concat("&finalDate=2018-01-01&operatorName=Beltrano")).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
        .andExpect(jsonPath("$.message").value("Se uma data for informada, a outra também precisa ser preenchida."))
        .andExpect(jsonPath("$.error").value("Error in your request"));
	}
	
	@Test
	public void testGetAllTransactionByAccoutIdAndDateRange_whenDontSendFinalDate() throws Exception {
	    this.mockMvc.perform(get(this.baseUrl.concat("&initialDate=2023-07-15")).contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
	        .andExpect(jsonPath("$.message").value("Se uma data for informada, a outra também precisa ser preenchida."))
	        .andExpect(jsonPath("$.error").value("Error in your request"));
	}
	
	@Test
	public void testGetAllTransactionByAccoutIdOperatorNameAndDateRange_whenDontSendInitialDate() throws Exception {
	    this.mockMvc.perform(get(this.baseUrl.concat("&finalDate=2018-01-01&operatorName=Beltrano")).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
        .andExpect(jsonPath("$.message").value("Se uma data for informada, a outra também precisa ser preenchida."))
        .andExpect(jsonPath("$.error").value("Error in your request"));
	}
	
	@Test
	public void testGetAllTransactionByAccoutIdOperatorNameAndDateRange_whenDontSendFinalDate() throws Exception {
	    this.mockMvc.perform(get(this.baseUrl.concat("&initialDate=2023-07-15&operatorName=Beltrano")).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
        .andExpect(jsonPath("$.message").value("Se uma data for informada, a outra também precisa ser preenchida."))
        .andExpect(jsonPath("$.error").value("Error in your request"));
	}
	
	@Test
	public void testGetAllTransactionByAccoutId_whenSendInvalidId() throws Exception {
		this.mockMvc.perform(get(this.baseUrlInvalidId).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
        .andExpect(jsonPath("$.message").value("O ID da conta deve ser um número válido."))
        .andExpect(jsonPath("$.error").value("Error in your request"));
	}
	
	@Test
	public void testGetAllTransactionByAccoutIdAndOperatorName_whenSendInvalidId() throws Exception {
		this.mockMvc.perform(get(this.baseUrlInvalidId.concat("&operatorName=Beltrano")).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
        .andExpect(jsonPath("$.message").value("O ID da conta deve ser um número válido."))
        .andExpect(jsonPath("$.error").value("Error in your request"));
	}
	
	@Test
	public void testGetAllTransactionByAccoutIdOperatorNameAndDateRange_whenSendInvalidId() throws Exception {
	    this.mockMvc.perform(get(this.baseUrlInvalidId.concat("&initialDate=2023-07-15&operatorName=Beltrano")).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
        .andExpect(jsonPath("$.message").value("O ID da conta deve ser um número válido."))
        .andExpect(jsonPath("$.error").value("Error in your request"));
	}
	
	@Test
	public void testGetAllTransactionByAccoutIdAndDateRange_whenSendInvalidId() throws Exception {
	    this.mockMvc.perform(get(this.baseUrlInvalidId.concat("&initialDate=2023-07-15")).contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
	        .andExpect(jsonPath("$.message").value("O ID da conta deve ser um número válido."))
	        .andExpect(jsonPath("$.error").value("Error in your request"));
	}
}
