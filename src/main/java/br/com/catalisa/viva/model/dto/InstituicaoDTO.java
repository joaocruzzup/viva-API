package br.com.catalisa.viva.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstituicaoDTO {
    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    private EnderecoDTO endereco;
}
