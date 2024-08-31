package example.br.cnpjsearchapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmpresaResponseDTO {

    @JsonProperty("razao_social")
    private String razaoSocial;

    @JsonProperty("estabelecimento")
    private EstabelecimentoDTO estabelecimentoDTO;

    // Construtor padrão
    public EmpresaResponseDTO() {
        // Inicializa os campos como null
        this.razaoSocial = null;
        this.estabelecimentoDTO = null;
    }

    // Construtor com parâmetros
    public EmpresaResponseDTO(String razaoSocial, EstabelecimentoDTO estabelecimentoDTO) {
        this.razaoSocial = razaoSocial;
        this.estabelecimentoDTO = estabelecimentoDTO;
    }

    // Getters e Setters
    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public EstabelecimentoDTO getEstabelecimentoDTO() {
        return estabelecimentoDTO;
    }

    public void setEstabelecimentoDTO(EstabelecimentoDTO estabelecimentoDTO) {
        this.estabelecimentoDTO = estabelecimentoDTO;
    }
}
