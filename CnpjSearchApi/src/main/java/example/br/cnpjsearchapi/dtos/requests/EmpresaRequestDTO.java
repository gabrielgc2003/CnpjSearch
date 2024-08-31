package example.br.cnpjsearchapi.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import example.br.cnpjsearchapi.dtos.CidadeDTO;
import example.br.cnpjsearchapi.dtos.EstabelecimentoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Classe de requisição para Empresa
public class EmpresaRequestDTO {

    @JsonProperty("razao_social")
    @NotNull(message = "A razão social não pode ser nula.") // Validação para campo não nulo
    @Size(min = 1, max = 255, message = "A razão social deve ter entre 1 e 255 caracteres.") // Validação para o intervalo de caracteres
    private String razaoSocial;

    @JsonProperty("estabelecimento")
    @Valid
    private EstabelecimentoDTO estabelecimento;

    // Construtor padrão
    public EmpresaRequestDTO() {
        this.razaoSocial = null;
        this.estabelecimento = null;
    }

    // Construtor com parâmetros
    public EmpresaRequestDTO(String razaoSocial, EstabelecimentoDTO estabelecimento) {
        this.razaoSocial = razaoSocial;
        this.estabelecimento = estabelecimento;
    }

    // Getters e Setters
    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public EstabelecimentoDTO getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(EstabelecimentoDTO estabelecimento) {
        this.estabelecimento = estabelecimento;
    }
}