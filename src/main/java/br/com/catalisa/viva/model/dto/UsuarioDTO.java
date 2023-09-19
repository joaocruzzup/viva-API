package br.com.catalisa.viva.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    @NotBlank
    private String nome;

    @NotBlank
    private String email;

}
