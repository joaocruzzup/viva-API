package br.com.catalisa.viva.controller;

import br.com.catalisa.viva.model.dto.ConsultaDTO;
import br.com.catalisa.viva.model.dto.DelecaoResponseDTO;
import br.com.catalisa.viva.model.dto.EnderecoDTO;
import br.com.catalisa.viva.service.ConsultaService;
import br.com.catalisa.viva.service.EnderecoService;
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
@RequestMapping("/api/enderecos")
public class EnderecoController {
    @Autowired
    private EnderecoService service;

    @Operation(summary = "Listar todas os endereços", method = "GET")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Listagem de endereços realizada com sucesso"))
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping
    ResponseEntity<List<EnderecoDTO>> listarTodos(){
        return ResponseEntity.ok(service.listarTodos());
    }

    @Operation(summary = "Buscar endereço por ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrada")
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(path = "{id}")
    ResponseEntity<EnderecoDTO> listarPorId(
            @Parameter(description = "ID da endereço a ser deletada", required = true, in = ParameterIn.PATH)
            @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Criar endereço", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "endereço criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida, confira os atributos preenchidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping
    ResponseEntity<EnderecoDTO> criar(
            @Parameter(description = "Dados da endereço a ser criada", required = true)
            @RequestBody @Valid EnderecoDTO enderecoDTO){
        EnderecoDTO novoEndereco = service.criar(enderecoDTO);
        return new ResponseEntity<>(novoEndereco, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar endereço por ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "endereço atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida, confira os atributos preenchidos"),
            @ApiResponse(responseCode = "404", description = "endereço não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PutMapping(path = "{id}")
    ResponseEntity<EnderecoDTO> atualizar(
            @Parameter(description = "ID do endereço a ser atualizada", required = true, in = ParameterIn.PATH)
            @PathVariable Long id,
            @Parameter(description = "Dados do endereço a ser atualizada", required = true)
            @RequestBody @Valid EnderecoDTO enderecoDTO) throws Exception {
        EnderecoDTO enderecoAtualizado = service.atualizar(id, enderecoDTO);
        return ResponseEntity.ok(enderecoAtualizado);
    }

    @Operation(summary = "Deletar endereço por ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "avaliação deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "avaliação não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(path = "{id}")
    ResponseEntity<DelecaoResponseDTO> deletar(
            @Parameter(description = "ID do endereço a ser deletada", required = true, in = ParameterIn.PATH)
            @PathVariable Long id) throws Exception {
        DelecaoResponseDTO delecaoResponse = service.deletar(id);
        return ResponseEntity.ok(delecaoResponse);
    }
}
