package br.com.catalisa.viva.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroBemEstarDTO {
    @NotBlank
    private String sentimentos;

    @NotBlank
    private String humor;

    @NotBlank
    private String padroesPensamento;

    @NotNull
    private UsuarioDTO usuario;
}
