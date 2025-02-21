package com.personal.desafio_itau_apirest.controllers;

import com.personal.desafio_itau_apirest.dtos.StatisticDTO;
import com.personal.desafio_itau_apirest.services.StatisticService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estatistica")
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping
    @Operation(description = "Endpoint responsável por retornar estatísticas das transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca retornada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
    })
    public ResponseEntity<StatisticDTO> returnStatistics(@RequestParam(value = "interval", required = false, defaultValue = "60") Integer interval) {
        return ResponseEntity.ok(statisticService.returnStatistics(interval));
    }
}
