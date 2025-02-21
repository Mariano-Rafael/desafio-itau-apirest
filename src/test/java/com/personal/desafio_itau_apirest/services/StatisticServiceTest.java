package com.personal.desafio_itau_apirest.services;

import com.personal.desafio_itau_apirest.dtos.StatisticDTO;
import com.personal.desafio_itau_apirest.dtos.TransactionDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StatisticServiceTest {

    @InjectMocks
    StatisticService statisticService;

    @Mock
    TransactionService transactionService;

    TransactionDTO transactionDTO;

    StatisticDTO statisticDTO;

    @BeforeEach
    void setUp() {
        transactionDTO = new TransactionDTO(900D, OffsetDateTime.now());
        statisticDTO = new StatisticDTO(1L, 900D, 900D, 900D, 900D);
    }

    @Test
    void returnSuccess() {
        when(transactionService.returnTransactions(60)).thenReturn(Collections.singletonList(transactionDTO));

        StatisticDTO statistic = statisticService.returnStatistics(60);

        verify(transactionService, times(1)).returnTransactions(60);

        assertThat(statistic).usingRecursiveComparison().isEqualTo(statisticDTO);
    }

    @Test
    void returnSuccessWithoutTransactions() {

        StatisticDTO statisticDTOWithoutTransactions = new StatisticDTO(0L, 0D, 0D, 0D, 0D);

        when(transactionService.returnTransactions(60)).thenReturn(Collections.emptyList());

        StatisticDTO statistic = statisticService.returnStatistics(60);

        verify(transactionService, times(1)).returnTransactions(60);

        assertThat(statistic).usingRecursiveComparison().isEqualTo(statisticDTOWithoutTransactions);
    }
}
