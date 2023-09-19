package br.com.catalisa.viva.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoResponseDTO {
    @NotNull
    private int pontuacao;

    @NotBlank
    private String comentario;

    @NotNull
    private ProfissionalSaudeDTO profissionalSaude;

    @NotNull
    private UsuarioDTO usuario;
}
