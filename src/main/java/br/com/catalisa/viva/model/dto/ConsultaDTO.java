package br.com.catalisa.viva.model.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaDTO {
    @NotNull
    private LocalDate dataConsulta;

    @NotNull
    private ProfissionalSaudeDTO profissionalSaude;

    @NotNull
    private UsuarioDTO usuario;
}
