package br.example.com.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
// Classe de comunicação Empresa
public class EmpresaDTO {

    @JsonProperty("razao_social")
    private String razaoSocial;

    @JsonProperty("estabelecimento")
    private EstabelecimentoDTO estabelecimento = new EstabelecimentoDTO();

    public EmpresaDTO() {
    }

    public EmpresaDTO(String razaoSocial, EstabelecimentoDTO estabelecimento) {
        this.razaoSocial = razaoSocial;
        this.estabelecimento = estabelecimento;
    }

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

    @Override
    public String toString() {
        return "EmpresaDTO{" +
                "razaoSocial='" + razaoSocial + '\'' +
                ", estabelecimento=" + estabelecimento +
                '}';
    }
}