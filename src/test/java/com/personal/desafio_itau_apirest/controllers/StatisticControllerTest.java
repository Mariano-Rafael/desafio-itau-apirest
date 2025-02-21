package com.personal.desafio_itau_apirest.controllers;

import com.personal.desafio_itau_apirest.dtos.StatisticDTO;
import com.personal.desafio_itau_apirest.services.StatisticService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class StatisticControllerTest {

    @InjectMocks
    StatisticController statisticController;

    @Mock
    StatisticService statisticService;

    StatisticDTO statisticDTO;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(statisticController).build();

        statisticDTO = new StatisticDTO(1L, 900D, 900D, 900D, 900D);
    }

    @Test
    void returnSuccessStatistics() throws Exception {

        when(statisticService.returnStatistics(60)).thenReturn(statisticDTO);

        mockMvc.perform(get("/estatistica")
                .param("interval", "60")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.count").value(statisticDTO.count()));
    }
}
