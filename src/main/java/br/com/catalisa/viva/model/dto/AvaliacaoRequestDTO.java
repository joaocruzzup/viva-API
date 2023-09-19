package br.com.catalisa.viva.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AvaliacaoRequestDTO {
    @NotNull
    private int pontuacao;

    @NotBlank
    private String comentario;

    @NotNull
    private Long profissionalSaudeId;

    @NotNull
    private Long usuarioId;
}
