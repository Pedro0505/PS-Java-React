package br.com.banco.entites;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity()
@Table(name = "transferencia")
public class Transferencia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "data_transferencia", nullable = false)
	private OffsetDateTime transferDate;

	@Column(name = "valor", nullable = false, precision = 20, scale = 2)
	private Double value;

	@Column(name = "tipo", nullable = false, length = 15)
	private String type;

	@Column(name = "nome_operador_transacao", nullable = false, length = 50)
	private String transactionOperatorName;

	@ManyToOne()
	@JoinColumn(name = "conta_id")
	private Conta conta;

	public Transferencia() {
	}

	public Transferencia(Long id, OffsetDateTime transferDate, Double value, String type,
			String transactionOperatorName, Conta conta) {
		super();
		this.id = id;
		this.transferDate = transferDate;
		this.value = value;
		this.type = type;
		this.transactionOperatorName = transactionOperatorName;
		this.conta = conta;
	}

	public OffsetDateTime getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(OffsetDateTime transferDate) {
		this.transferDate = transferDate;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTransactionOperatorName() {
		return transactionOperatorName;
	}

	public void setTransactionOperatorName(String transactionOperatorName) {
		this.transactionOperatorName = transactionOperatorName;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
}
