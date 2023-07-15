package br.com.banco.entites;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity()
@Table(name = "conta")
public class Conta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_conta")
	private Long id;

	@Column(name = "nome_responsavel", nullable = false)
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "conta",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Transferencia> transferencia = new ArrayList<>();

	public Conta() {
	}

	public Conta(Long id, String name, List<Transferencia> transferencia) {
		super();
		this.id = id;
		this.name = name;
		this.transferencia = transferencia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Transferencia> getTransferencia() {
		return transferencia;
	}

	public void setTransferencia(List<Transferencia> transferencia) {
		this.transferencia = transferencia;
	}
}
