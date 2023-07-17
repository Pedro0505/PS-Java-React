package br.com.banco.services;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.dto.TransferenciaSearchDto;
import br.com.banco.entites.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;
import br.com.banco.services.exception.BadRequestException;
import br.com.banco.utils.SerializeDates;

@Service
public class TransferenciaService {
	@Autowired
	private TransferenciaRepository repository;

	public List<Transferencia> getAllFiltered(Optional<String> operatorName, Optional<String> initialDate, Optional<String> finalDate, Optional<String> accountId) {
		TransferenciaSearchDto transferencia = this.transferenciaSerialize(operatorName, initialDate, finalDate, accountId);
		
		OffsetDateTime startDateTime = transferencia.getInitialDate();
		OffsetDateTime endDateTime = transferencia.getFinalDate();
		String operator = transferencia.getOperatorName();
		
		this.verifyDates(startDateTime, endDateTime);
		
		if (startDateTime != null && endDateTime != null && operator != null) {
			return this.getAllByDateRangeAndOperatorName(transferencia);
		} else if (startDateTime != null && endDateTime != null) {			
			return this.getAllByDateRange(transferencia);
		} else if (operator != null) {
			return this.getAllByOperatorName(transferencia);
		} else {
			return this.getAllByAccountId(transferencia);
		}
	}

	public List<Transferencia> getAllByAccountId(TransferenciaSearchDto transferencia) {
		return this.repository.findAllByAccountId(transferencia.getAccountId());
	}

	public List<Transferencia> getAllByOperatorName(TransferenciaSearchDto transferencia) {
		return this.repository.findAllByOperatorNameAndContaId(transferencia.getOperatorName(), transferencia.getAccountId());
	}

	public List<Transferencia> getAllByDateRange(TransferenciaSearchDto transferencia) {
		OffsetDateTime startDateTime = transferencia.getInitialDate();
		OffsetDateTime endDateTime = transferencia.getFinalDate();

		this.finalDateIsAfter(startDateTime, endDateTime);

		return this.repository.findAllByAccountIdBetweenTransferDate(startDateTime, endDateTime, transferencia.getAccountId());
	}

	public List<Transferencia> getAllByDateRangeAndOperatorName(TransferenciaSearchDto transferencia) {
		OffsetDateTime startDateTime = transferencia.getInitialDate();
		OffsetDateTime endDateTime = transferencia.getFinalDate();
		String operator = transferencia.getOperatorName();

		this.finalDateIsAfter(startDateTime, endDateTime);

		return this.repository.findAllByOperatorNameAndAccountIdBetweenTransferDate(operator, startDateTime, endDateTime,
				transferencia.getAccountId());
	}
	
	private void finalDateIsAfter(OffsetDateTime initialDate, OffsetDateTime finalDate) {
		if (initialDate.isAfter(finalDate)) {
			throw new BadRequestException("A data de fim não pode ser anterior à data de início.");
		}
	}
	
	private void verifyDates(OffsetDateTime initialDate, OffsetDateTime finalDate) {
		if ((initialDate != null && finalDate == null) || (initialDate == null && finalDate != null)) {
			throw new BadRequestException("Se uma data for informada, a outra também precisa ser preenchida.");
		}
	}
	
	private TransferenciaSearchDto transferenciaSerialize(Optional<String> operatorName, Optional<String> initialDate, Optional<String> finalDate, Optional<String> accountId) {
	    try {
	        OffsetDateTime startDateTime = initialDate.map(SerializeDates::stringToOffSetDateTime).orElse(null);
	        OffsetDateTime endDateTime = finalDate.map(SerializeDates::stringToOffSetDateTime).orElse(null);
	        String operator = operatorName.orElse(null);
	        Long id = accountId.map(Long::parseLong).orElse(null);

	        return new TransferenciaSearchDto(id, startDateTime, endDateTime, operator);
	    } catch (NumberFormatException e) {
	        throw new BadRequestException("O ID da conta deve ser um número válido.");
	    }
	}
}
