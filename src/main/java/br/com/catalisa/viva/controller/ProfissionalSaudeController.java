package br.com.catalisa.viva.controller;

import br.com.catalisa.viva.model.dto.DelecaoResponseDTO;
import br.com.catalisa.viva.model.dto.ProfissionalSaudeDTO;
import br.com.catalisa.viva.service.ProfissionalSaudeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/profissionais")
public class ProfissionalSaudeController {
    @Autowired
    private ProfissionalSaudeService service;

    @Operation(summary = "Listar todas os profissionais de saúde", method = "GET")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Listagem de profissionais de saúde realizada com sucesso"))
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping
    ResponseEntity<List<ProfissionalSaudeDTO>> listarTodos(){
        return ResponseEntity.ok(service.listarTodos());
    }

    @Operation(summary = "Buscar profissionais de saúde por ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "profissionais de saúde encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "profissionais de saúde não encontrada")
    })
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(path = "{id}")
    ResponseEntity<ProfissionalSaudeDTO> listarPorId(
            @Parameter(description = "ID da profissionais de saúde a ser listada", required = true, in = ParameterIn.PATH)
            @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Criar profissionais de saúde", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "profissionais de saúde criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida, confira os atributos preenchidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping
    ResponseEntity<ProfissionalSaudeDTO> criar(
            @Parameter(description = "Dados do profissionais de saúde a ser criada", required = true)
            @RequestBody @Valid ProfissionalSaudeDTO profissionalSaudeDTO){
        ProfissionalSaudeDTO novoProfissional = service.criar(profissionalSaudeDTO);
        return new ResponseEntity<>(novoProfissional, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar profissionais de saúde por ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "profissionais de saúde atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida, confira os atributos preenchidos"),
            @ApiResponse(responseCode = "404", description = "profissionais de saúde não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping(path = "{id}")
    ResponseEntity<ProfissionalSaudeDTO> atualizar(
            @Parameter(description = "ID da profissionais de saúde a ser atualizada", required = true, in = ParameterIn.PATH)
            @PathVariable Long id,
            @Parameter(description = "Dados do profissionais de saúde a ser atualizada", required = true)
            @RequestBody @Valid ProfissionalSaudeDTO profissionalSaudeDTO) throws Exception {
        ProfissionalSaudeDTO profissionalAtualizado = service.atualizar(id, profissionalSaudeDTO);
        return ResponseEntity.ok(profissionalAtualizado);
    }

    @Operation(summary = "Deletar profissionais de saúde por ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "profissionais de saúde deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "profissionais de saúde não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(path = "{id}")
    ResponseEntity<DelecaoResponseDTO> deletar(
            @Parameter(description = "ID da profissionais de saúde a ser deletada", required = true, in = ParameterIn.PATH)
            @PathVariable Long id) throws Exception {
        DelecaoResponseDTO delecaoResponse = service.deletar(id);
        return ResponseEntity.ok(delecaoResponse);
    }
}
