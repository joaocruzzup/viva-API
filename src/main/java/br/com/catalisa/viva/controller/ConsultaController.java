package br.com.catalisa.viva.controller;

import br.com.catalisa.viva.exception.EntidadeNaoEncontradaException;
import br.com.catalisa.viva.model.dto.ConsultaDTO;
import br.com.catalisa.viva.model.dto.DelecaoResponseDTO;
import br.com.catalisa.viva.service.ConsultaService;
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
@RequestMapping("/api/consultas")
public class ConsultaController {
    @Autowired
    private ConsultaService service;

    @Operation(summary = "Listar todas as consultas", method = "GET")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Listagem de consultas realizada com sucesso"))
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping
    ResponseEntity<List<ConsultaDTO>> listarTodos(){
        return ResponseEntity.ok(service.listarTodos());
    }


    @Operation(summary = "Buscar consultas por ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Consulta não encontrada")
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(path = "{id}")
    ResponseEntity<ConsultaDTO> listarPorId(
            @Parameter(description = "ID da consulta a ser deletada", required = true, in = ParameterIn.PATH)
            @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Criar consulta", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "consulta criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida, confira os atributos preenchidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping
    ResponseEntity<ConsultaDTO> criar(
            @Parameter(description = "Dados da consulta a ser criada", required = true)
            @RequestBody @Valid ConsultaDTO consultaDTO){
        ConsultaDTO novaConsulta = service.criar(consultaDTO);
        return new ResponseEntity<>(novaConsulta, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar consulta por ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida, confira os atributos preenchidos"),
            @ApiResponse(responseCode = "404", description = "Consulta não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PutMapping(path = "{id}")
    ResponseEntity<ConsultaDTO> atualizar(
            @Parameter(description = "ID da avaliação a ser atualizada", required = true, in = ParameterIn.PATH)
            @PathVariable Long id,
            @Parameter(description = "Dados da avaliação a ser atualizada", required = true)
            @RequestBody @Valid ConsultaDTO consultaDTO) throws Exception {
        ConsultaDTO consultaAtualizada = service.atualizar(id, consultaDTO);
        return ResponseEntity.ok(consultaAtualizada);
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
            @Parameter(description = "ID da consulta a ser deletada", required = true, in = ParameterIn.PATH)
            @PathVariable Long id) throws EntidadeNaoEncontradaException {
        DelecaoResponseDTO delecaoResponse = service.deletar(id);
        return ResponseEntity.ok(delecaoResponse);
    }
}
