package br.com.banco.dto;

import java.time.OffsetDateTime;

public class TransferenciaSearchDto {
	private Long accountId;
	
	private OffsetDateTime initialDate;
	
	private OffsetDateTime finalDate;
	
	private String operatorName;

	public TransferenciaSearchDto(Long accountId, OffsetDateTime initialDate, OffsetDateTime finalDate, String operatorName) {
		super();
		this.accountId = accountId;
		this.initialDate = initialDate;
		this.finalDate = finalDate;
		this.operatorName = operatorName;
	}
	
	public TransferenciaSearchDto(Long accountId, OffsetDateTime initialDate, OffsetDateTime finalDate) {
		super();
		this.accountId = accountId;
		this.initialDate = initialDate;
		this.finalDate = finalDate;
	}
	
	public TransferenciaSearchDto(Long accountId,  String operatorName) {
		super();
		this.accountId = accountId;
		this.operatorName = operatorName;
	}
	
	public TransferenciaSearchDto() {
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public OffsetDateTime getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(OffsetDateTime initialDate) {
		this.initialDate = initialDate;
	}

	public OffsetDateTime getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(OffsetDateTime finalDate) {
		this.finalDate = finalDate;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}	
}
