package br.com.catalisa.viva.controller;

import br.com.catalisa.viva.model.dto.AvaliacaoRequestDTO;
import br.com.catalisa.viva.model.dto.AvaliacaoResponseDTO;
import br.com.catalisa.viva.model.dto.DelecaoResponseDTO;
import br.com.catalisa.viva.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/avaliacoes")
@Tag(name = "Avaliacoes", description = "Endpoint para gerenciamento de avaliações dos psicólogos")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoService service;

    @Operation(summary = "Listar todas as avaliações", method = "GET")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Listagem de avaliações realizada com sucesso"))
    @GetMapping
    ResponseEntity<List<AvaliacaoResponseDTO>> listarTodos(){
        return ResponseEntity.ok(service.listarTodos());
    }

    @Operation(summary = "Buscar avaliação por ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Avaliação encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Avaliação não encontrada")
    })
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(path = "{id}")
    ResponseEntity<AvaliacaoResponseDTO> listarPorId(
            @Parameter(description = "ID da avaliação a ser listada", required = true, in = ParameterIn.PATH)
            @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Criar avaliação", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "avaliação criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida, confira os atributos preenchidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping
    ResponseEntity<AvaliacaoResponseDTO> criar(
            @Parameter(description = "Dados da avaliação a ser criada", required = true)
            @RequestBody @Valid AvaliacaoRequestDTO avaliacaoDTO) throws Exception {
        AvaliacaoResponseDTO novaAvaliacao = service.criar(avaliacaoDTO);
        return new ResponseEntity<>(novaAvaliacao, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar avaliação por ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Avaliação atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida, confira os atributos preenchidos"),
            @ApiResponse(responseCode = "404", description = "Avaliação não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping(path = "{id}")
    ResponseEntity<AvaliacaoResponseDTO> atualizar(
            @Parameter(description = "ID da avaliação a ser atualizada", required = true, in = ParameterIn.PATH)
            @PathVariable Long id,
            @Parameter(description = "Dados da avaliação a ser atualizada", required = true)
            @RequestBody @Valid AvaliacaoResponseDTO avaliacaoDTO) throws Exception {
        AvaliacaoResponseDTO avaliacaoAtualizada = service.atualizar(id, avaliacaoDTO);
        return ResponseEntity.ok(avaliacaoAtualizada);
    }

    @Operation(summary = "Deletar avaliação por ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "avaliação deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "avaliação não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(path = "{id}")
    ResponseEntity<DelecaoResponseDTO> deletar(
            @Parameter(description = "ID da avaliação a ser deletada", required = true, in = ParameterIn.PATH)
            @PathVariable Long id) throws Exception {
        DelecaoResponseDTO delecaoResponse = service.deletar(id);
        return ResponseEntity.ok(delecaoResponse);
    }
}
