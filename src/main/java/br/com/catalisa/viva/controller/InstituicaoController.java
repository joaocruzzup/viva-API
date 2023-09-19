package br.com.catalisa.viva.controller;

import br.com.catalisa.viva.model.dto.DelecaoResponseDTO;
import br.com.catalisa.viva.model.dto.EnderecoDTO;
import br.com.catalisa.viva.model.dto.InstituicaoDTO;
import br.com.catalisa.viva.service.EnderecoService;
import br.com.catalisa.viva.service.InstituicaoService;
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
@RequestMapping("/api/instituicoes")
public class InstituicaoController {
    @Autowired
    private InstituicaoService service;

    @Operation(summary = "Listar todas as instituições", method = "GET")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Listagem de instituições realizada com sucesso"))
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping
    ResponseEntity<List<InstituicaoDTO>> listarTodos(){
        return ResponseEntity.ok(service.listarTodos());
    }

    @Operation(summary = "Buscar instituição por ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "instituição encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "instituição não encontrada")
    })
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(path = "{id}")
    ResponseEntity<InstituicaoDTO> listarPorId(
            @Parameter(description = "ID da instituição a ser listada", required = true, in = ParameterIn.PATH)
            @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Criar instituição", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "instituição criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida, confira os atributos preenchidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping
    ResponseEntity<InstituicaoDTO> criar(
            @Parameter(description = "Dados da instituição a ser criada", required = true)
            @RequestBody @Valid InstituicaoDTO instituicaoDTO){
        InstituicaoDTO novaInstituicao = service.criar(instituicaoDTO);
        return new ResponseEntity<>(novaInstituicao, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar instituição por ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "instituição atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida, confira os atributos preenchidos"),
            @ApiResponse(responseCode = "404", description = "instituição não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PutMapping(path = "{id}")
    ResponseEntity<InstituicaoDTO> atualizar(
            @Parameter(description = "ID da instituição a ser atualizada", required = true, in = ParameterIn.PATH)
            @PathVariable Long id,
            @Parameter(description = "Dados da instituição a ser atualizada", required = true)
            @RequestBody @Valid InstituicaoDTO instituicaoDTO) throws Exception {
        InstituicaoDTO instituicaoAtualizada = service.atualizar(id, instituicaoDTO);
        return ResponseEntity.ok(instituicaoAtualizada);
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
