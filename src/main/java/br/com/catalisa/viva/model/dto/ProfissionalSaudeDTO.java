package br.com.catalisa.viva.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfissionalSaudeDTO {
    @NotBlank
    private String nome;

    @NotBlank
    private String especializacao;

    @NotBlank
    private String disponibilidade;

    @NotNull
    private Double precoConsulta;

    @NotBlank
    private String contato;

    private String localizacao;

    private EnderecoDTO endereco;
}
