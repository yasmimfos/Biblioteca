package com.example.livros.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record LivroRecordDto(@NotBlank String titulo, @NotBlank String autor, @NotBlank String status, BigDecimal classificacao, @NotNull int paginas, int pagAtual) {

}
