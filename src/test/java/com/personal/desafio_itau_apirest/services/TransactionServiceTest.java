package com.personal.desafio_itau_apirest.services;

import com.personal.desafio_itau_apirest.dtos.StatisticDTO;
import com.personal.desafio_itau_apirest.dtos.TransactionDTO;
import com.personal.desafio_itau_apirest.exceptions.UnprocessableEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    TransactionService transactionService;

    TransactionDTO transactionDTO;

    StatisticDTO statisticDTO;

    @BeforeEach
    void setUp() {
        transactionDTO = new TransactionDTO(900D, OffsetDateTime.now());
        statisticDTO = new StatisticDTO(1L, 900D, 900D, 900D, 900D);
    }

    @Test
    void inputTransactionSuccess() {

        transactionService.addTransaction(transactionDTO);

        List<TransactionDTO> transactions = transactionService.returnTransactions(900);
        assertTrue(transactions.contains(transactionDTO));
    }

    @Test
    void returnExceptionWhenValueIsNegative() {

        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class, () -> transactionService.addTransaction(new TransactionDTO(-90D, OffsetDateTime.now())));

        assertEquals("Valor da transação deve ser maior que zero", exception.getMessage());
    }

    @Test
    void returnExceptionWhenTimestampIsFuture() {

        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class, () -> transactionService.addTransaction(new TransactionDTO(90D, OffsetDateTime.now().plusYears(3))));

        assertEquals("Data/Hora da transação não pode ser futura", exception.getMessage());
    }

    @Test
    void returnSuccessClearTransactions() {

        transactionService.clearTransactions();

        List<TransactionDTO> transactions = transactionService.returnTransactions(900);
        assertTrue(transactions.isEmpty());
    }

    @Test
    void returnSuccessReturnTransactions() {

        TransactionDTO transactionDTOFalse = new TransactionDTO(900D, OffsetDateTime.now().minusMonths(1));

        transactionService.addTransaction(transactionDTO);
        transactionService.addTransaction(transactionDTOFalse);

        List<TransactionDTO> transaction = transactionService.returnTransactions(60);

        assertTrue(transaction.contains(transactionDTO));
        assertFalse(transaction.contains(transactionDTOFalse));
    }

}
