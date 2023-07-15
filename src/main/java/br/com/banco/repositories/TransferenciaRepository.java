package br.com.banco.repositories;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.banco.entites.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    @Query("SELECT t FROM Transferencia t WHERE t.conta.id = :accountId")
    List<Transferencia> findAllByAccountId(Long accountId);

    @Query("SELECT t FROM Transferencia t WHERE t.transactionOperatorName = :operatorName AND t.conta.id = :accountId")
    List<Transferencia> findAllByOperatorNameAndContaId(String operatorName, Long accountId);
    
    @Query("SELECT t FROM Transferencia t WHERE t.transferDate BETWEEN :initialDate AND :finalDate AND t.conta.id = :accountId")
    List<Transferencia> findAllByAccountIdBetweenTransferDate(OffsetDateTime initialDate, OffsetDateTime finalDate, Long accountId);
    
    @Query("SELECT t FROM Transferencia t WHERE t.transactionOperatorName = :operatorName " +
            "AND t.transferDate BETWEEN :initialDate AND :finalDate " +
            "AND t.conta.id = :accountId"
     )
    List<Transferencia> findAllByOperatorNameAndAccountIdBetweenTransferDate(String operatorName, OffsetDateTime initialDate, OffsetDateTime finalDate, Long accountId);
}
