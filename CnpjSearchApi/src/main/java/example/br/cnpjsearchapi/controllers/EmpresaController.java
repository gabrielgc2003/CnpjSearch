package example.br.cnpjsearchapi.controllers;

import example.br.cnpjsearchapi.dtos.requests.EmpresaRequestDTO;
import example.br.cnpjsearchapi.dtos.responses.EmpresaResponseDTO;
import example.br.cnpjsearchapi.exceptions.ApiException;
import example.br.cnpjsearchapi.services.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controlador para gerenciar operações relacionadas a CNPJ.
 * Fornece endpoints para buscar e criar informações sobre empresas usando CNPJ.
 */
@RestController
@RequestMapping("/cnpj")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    /**
     * Endpoint para buscar informações de uma empresa pelo CNPJ.
     *
     * @param cnpj O CNPJ da empresa a ser consultada.
     * @return Resposta com os dados da empresa se encontrada, ou uma resposta 404 se não encontrada.
     * @throws ApiException Caso ocorra um erro ao consultar a empresa.
     */
    @Operation(summary = "Buscar empresa pelo CNPJ", description = "Retorna os detalhes da empresa para o CNPJ fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada")
    })
    @GetMapping("{cnpj}")
    public ResponseEntity<EmpresaResponseDTO> getCnpj(@PathVariable("cnpj") String cnpj) throws ApiException {
        Optional<EmpresaResponseDTO> empresaResponseDTO = empresaService.getCnpj(cnpj);
        return empresaResponseDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para criar uma nova entrada de empresa com base no CNPJ fornecido.
     *
     * @param empresaRequestDTO Dados da empresa a serem criados.
     * @return Resposta com os dados da empresa criada.
     */
    @Operation(summary = "Criar uma nova empresa", description = "Cria uma nova entrada de empresa com base no CNPJ fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa criada com sucesso")
    })
    @PostMapping
    public ResponseEntity<EmpresaResponseDTO> createCnpj(@Valid @RequestBody EmpresaRequestDTO empresaRequestDTO) {
        Optional<EmpresaResponseDTO> empresaResponse = empresaService.createCnpj(empresaRequestDTO);
        return empresaResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(400).build());
    }
}
