package com.personal.desafio_itau_apirest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.personal.desafio_itau_apirest.dtos.StatisticDTO;
import com.personal.desafio_itau_apirest.dtos.TransactionDTO;
import com.personal.desafio_itau_apirest.exceptions.UnprocessableEntity;
import com.personal.desafio_itau_apirest.services.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

    @InjectMocks
    TransactionController transactionController;

    @Mock
    TransactionService transactionService;
    TransactionDTO transactionDTO;
    MockMvc mockMvc;
    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();

        transactionDTO = new TransactionDTO(200D, OffsetDateTime.of(2025, 2, 21, 10, 28, 0, 0, ZoneOffset.UTC));
    }

    @Test
    void returnSuccessCreateTransaction() throws Exception {

        doNothing().when(transactionService).addTransaction(transactionDTO);

        mockMvc.perform(post("/transacao")
                .content(objectMapper.writeValueAsString(transactionDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void returnException() throws Exception {
        doThrow(new UnprocessableEntity("Erro na requisição"))
                .when(transactionService).addTransaction(transactionDTO);

        mockMvc.perform(post("/transacao")
                .content(objectMapper.writeValueAsString(transactionDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void returnDeleteTransactionsWithSuccess() throws Exception {

        doNothing().when(transactionService).clearTransactions();

        mockMvc.perform(delete("/transacao"))
                .andExpect(status().isOk());
    }
}
