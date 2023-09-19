package br.com.catalisa.viva.controller;

import br.com.catalisa.viva.model.dto.DelecaoResponseDTO;
import br.com.catalisa.viva.model.dto.InstituicaoDTO;
import br.com.catalisa.viva.model.dto.RegistroBemEstarDTO;
import br.com.catalisa.viva.service.InstituicaoService;
import br.com.catalisa.viva.service.RegistroBemEstarService;
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
@RequestMapping("/api/bemestar")
public class RegistroBemEstarController {
    @Autowired
    private RegistroBemEstarService service;

    @Operation(summary = "Listar todas os registros de bem estar", method = "GET")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Listagem dos registros de bem estar realizada com sucesso"))
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping
    ResponseEntity<List<RegistroBemEstarDTO>> listarTodos(){
        return ResponseEntity.ok(service.listarTodos());
    }

    @Operation(summary = "Buscar os registros de bem estar por ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "registros de bem estar encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "registros de bem estar não encontrada")
    })
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(path = "{id}")
    ResponseEntity<RegistroBemEstarDTO> listarPorId(
            @Parameter(description = "ID da registros de bem estar a ser listada", required = true, in = ParameterIn.PATH)
            @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Criar registros de bem estar", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "registros de bem estar criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida, confira os atributos preenchidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    ResponseEntity<RegistroBemEstarDTO> criar(
            @Parameter(description = "Dados da registros de bem estar a ser criada", required = true)
            @RequestBody @Valid RegistroBemEstarDTO registroBemEstarDTO){
        RegistroBemEstarDTO novoRegistro = service.criar(registroBemEstarDTO);
        return new ResponseEntity<>(novoRegistro, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar registros de bem estar por ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "registros de bem estar atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida, confira os atributos preenchidos"),
            @ApiResponse(responseCode = "404", description = "registros de bem estar não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping(path = "{id}")
    ResponseEntity<RegistroBemEstarDTO> atualizar(
            @Parameter(description = "ID da registros de bem estar a ser atualizada", required = true, in = ParameterIn.PATH)
            @PathVariable Long id,
            @Parameter(description = "Dados da registros de bem estar a ser atualizada", required = true)
            @RequestBody @Valid RegistroBemEstarDTO registroBemEstarDTO) throws Exception {
        RegistroBemEstarDTO registroAtualizado = service.atualizar(id, registroBemEstarDTO);
        return ResponseEntity.ok(registroAtualizado);
    }

    @Operation(summary = "Deletar registros de bem estar por ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "registros de bem estar deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "registros de bem estar não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(path = "{id}")
    ResponseEntity<DelecaoResponseDTO> deletar(
            @Parameter(description = "ID da registros de bem estar a ser deletada", required = true, in = ParameterIn.PATH)
            @PathVariable Long id) throws Exception {
        DelecaoResponseDTO delecaoResponse = service.deletar(id);
        return ResponseEntity.ok(delecaoResponse);
    }
}
