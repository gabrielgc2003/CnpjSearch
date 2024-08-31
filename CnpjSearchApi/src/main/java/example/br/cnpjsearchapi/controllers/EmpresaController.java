package example.br.cnpjsearchapi.controllers;

import example.br.cnpjsearchapi.dtos.requests.EmpresaRequestDTO;
import example.br.cnpjsearchapi.dtos.responses.EmpresaResponseDTO;
import example.br.cnpjsearchapi.exceptions.ApiException;
import example.br.cnpjsearchapi.services.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cnpj")
public class EmpresaController {
    @Autowired
    public EmpresaService empresaService;

    @GetMapping("{cnpj}")
    public ResponseEntity<EmpresaResponseDTO> getCnpj(@PathVariable("cnpj") String cnpj) throws ApiException {
        Optional<EmpresaResponseDTO> empresaResponseDTO = empresaService.getCnpj(cnpj);
        return empresaResponseDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity createCnpj(@Valid @RequestBody EmpresaRequestDTO empresaRequestDTO) {
        Optional<EmpresaResponseDTO> empresaResponse = empresaService.createCnpj(empresaRequestDTO);
        return ResponseEntity.ok(empresaResponse);
    }
}
