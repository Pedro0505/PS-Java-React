package br.com.banco.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.entites.Conta;
import br.com.banco.services.ContaService;

@RestController
@RequestMapping(path = "/contas")
@CrossOrigin()
public class ContaController {
	@Autowired
	private ContaService service;
	@GetMapping()
	public ResponseEntity<List<Conta>> getAllAccounts() {
		List<Conta> accounts = this.service.getAllAccounts();
		
		return ResponseEntity.ok().body(accounts);
	}
}
