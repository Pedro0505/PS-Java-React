package br.com.banco.services;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.entites.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;
import br.com.banco.utils.SerializeDates;

@Service
public class TransferenciaService {
	@Autowired
	private TransferenciaRepository repository;

	public List<Transferencia> getAllFiltered(Optional<String> operatorName, Optional<String> initialDate, Optional<String> finalDate, String accountId) {
		String startDateTime = initialDate.orElse(null);
		String endDateTime = finalDate.orElse(null);
		String operator = operatorName.orElse(null);
		Long id = Long.parseLong(accountId);

		if (startDateTime != null && endDateTime != null && operator != null) {
			return this.getAllByDateRangeAndOperatorName(operator, startDateTime, endDateTime, id);
		} else if (startDateTime != null && endDateTime != null) {
			return this.getAllByDateRange(startDateTime, endDateTime, id);
		} else if (operator != null) {
			return this.getAllByOperatorName(operator, id);
		} else {
			return this.getAllByAccountId(id);
		}
	}

	public List<Transferencia> getAllByAccountId(Long accountId) {
		return this.repository.findAllByAccountId(accountId);
	}

	public List<Transferencia> getAllByOperatorName(String operatorName, Long accountId) {
		return this.repository.findAllByOperatorNameAndContaId(operatorName, accountId);
	}

	public List<Transferencia> getAllByDateRange(String initialDate, String finalDate, Long accountId) {
		OffsetDateTime startDate = SerializeDates.stringToOffSetDateTime(initialDate);
		OffsetDateTime endDate = SerializeDates.stringToOffSetDateTime(finalDate);

		return this.repository.findAllByAccountIdBetweenTransferDate(startDate, endDate, accountId);
	}

	public List<Transferencia> getAllByDateRangeAndOperatorName(String operatorName, String initialDate,
			String finalDate, Long accountId) {
		OffsetDateTime startDate = SerializeDates.stringToOffSetDateTime(initialDate);
		OffsetDateTime endDate = SerializeDates.stringToOffSetDateTime(finalDate);

		return this.repository.findAllByOperatorNameAndAccountIdBetweenTransferDate(operatorName, startDate, endDate,
				accountId);
	}
}
