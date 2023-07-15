package br.com.banco.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.entites.Transferencia;
import br.com.banco.services.TransferenciaService;

@RestController
@RequestMapping(path = "/transferencias")
@CrossOrigin()
public class TransferenciaController {
	@Autowired
	private TransferenciaService service;

	@GetMapping()
	public ResponseEntity<List<Transferencia>> getAllTransferenciasByOperatorNameAndDate(
			@RequestParam(value = "initialDate") Optional<String> initialDate,
			@RequestParam(value = "finalDate") Optional<String> finalDate,
			@RequestParam(value = "operatorName") Optional<String> operatorName,
			@RequestParam(value = "accountId") String accountId) {

		List<Transferencia> transactions = this.service.getAllFiltered(operatorName, initialDate, finalDate, accountId);

		return ResponseEntity.ok().body(transactions);
	}
}
