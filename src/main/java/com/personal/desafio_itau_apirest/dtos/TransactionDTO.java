package com.personal.desafio_itau_apirest.dtos;

import java.time.OffsetDateTime;

public record TransactionDTO(Double value, OffsetDateTime timestamp) {
}
