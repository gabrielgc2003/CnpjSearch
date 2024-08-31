package example.br.cnpjsearchapi.controllers;

import example.br.cnpjsearchapi.dtos.EmpresaResponseDTO;
import example.br.cnpjsearchapi.exceptions.ApiException;
import example.br.cnpjsearchapi.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
