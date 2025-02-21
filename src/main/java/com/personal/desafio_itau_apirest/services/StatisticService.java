package com.personal.desafio_itau_apirest.services;

import com.personal.desafio_itau_apirest.dtos.StatisticDTO;
import com.personal.desafio_itau_apirest.dtos.TransactionDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticService {

    public final TransactionService transactionService;

    public StatisticDTO returnStatistics(Integer interval) {

        log.info("--- Retornando estatísticas dos últimos {} segundos ---", interval);
        List<TransactionDTO> transactions = transactionService.returnTransactions(interval);

        if (transactions.isEmpty()) {
            return new StatisticDTO(0L, 0D, 0D, 0D, 0D);
        }

        DoubleSummaryStatistics statistics = transactions.stream()
                .mapToDouble(TransactionDTO::value)
                .summaryStatistics();

        log.info("--- Estatísticas retornadas com sucesso ---");
        return new StatisticDTO(statistics.getCount(),
                statistics.getSum(),
                statistics.getAverage(),
                statistics.getMin(),
                statistics.getMax());
    }
}
