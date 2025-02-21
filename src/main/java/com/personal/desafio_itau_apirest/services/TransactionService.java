package com.personal.desafio_itau_apirest.services;

import com.personal.desafio_itau_apirest.dtos.TransactionDTO;
import com.personal.desafio_itau_apirest.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final List<TransactionDTO> transactions = new ArrayList<>();

    public void addTransaction(TransactionDTO transaction) {

        log.info("--- Adicionando transação {}", transaction + " ---");
        if (transaction.timestamp().isAfter(OffsetDateTime.now())) {

            log.error("--- Data/hora da transação não podem ser futuras ---");
            throw new UnprocessableEntity("Data/Hora da transação não pode ser futura");
        }
        if (transaction.value() <= 0) {

            log.error("--- Valor da transação deve ser maior que zero ---");
            throw new UnprocessableEntity("Valor da transação deve ser maior que zero");
        }

        transactions.add(transaction);
        log.info("--- Transação adicionada com sucesso ---");
    }

    public void clearTransactions() {

        log.info("--- Limpando transações ---");
        transactions.clear();
        log.info("--- Transações excluídas com sucesso ---");
    }

    public List<TransactionDTO> returnTransactions(Integer interval) {

        log.info("--- Retornando transações dos últimos {} segundos ---", interval);
        OffsetDateTime timestampInterval = OffsetDateTime.now().minusSeconds(interval);

        return transactions.stream()
                .filter(transaction -> transaction.timestamp().isAfter(timestampInterval))
                .toList();
    }
}
