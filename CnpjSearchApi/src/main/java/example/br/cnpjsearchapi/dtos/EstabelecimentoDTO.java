package example.br.cnpjsearchapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EstabelecimentoDTO {

    @JsonProperty("cnpj")
    private String cnpj;

    @JsonProperty("situacao_cadastral")
    private String situacaoCadastral;

    @JsonProperty("data_situacao_cadastral")
    private String dataSituacaoCadastral;

    @JsonProperty("tipo_logradouro")
    private String tipoLogradouro;

    @JsonProperty("logradouro")
    private String logradouro;

    @JsonProperty("numero")
    private String numero;

    @JsonProperty("complemento")
    private String complemento;

    @JsonProperty("cep")
    private String cep;

    @JsonProperty("bairro")
    private String bairro;

    @JsonProperty("ddd1")
    private String ddd;

    @JsonProperty("telefone1")
    private String telefone;

    @JsonProperty("cidade")
    private CidadeDTO cidade;

    // Construtor padrão
    public EstabelecimentoDTO() {

    }

    // Construtor com parâmetros
    public EstabelecimentoDTO(String cnpj, String situacaoCadastral, String dataSituacaoCadastral, String tipoLogradouro, String logradouro, String numero, String complemento, String cep, String bairro, String ddd, String telefone, CidadeDTO cidade) {
        this.cnpj = cnpj;
        this.situacaoCadastral = situacaoCadastral;
        this.dataSituacaoCadastral = dataSituacaoCadastral;
        this.tipoLogradouro = tipoLogradouro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.bairro = bairro;
        this.ddd = ddd;
        this.telefone = telefone;
        this.cidade = cidade;
    }

    // Getters e Setters

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getSituacaoCadastral() {
        return situacaoCadastral;
    }

    public void setSituacaoCadastral(String situacaoCadastral) {
        this.situacaoCadastral = situacaoCadastral;
    }

    public String getDataSituacaoCadastral() {
        return dataSituacaoCadastral;
    }

    public void setDataSituacaoCadastral(String dataSituacaoCadastral) {
        this.dataSituacaoCadastral = dataSituacaoCadastral;
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public CidadeDTO getCidade() {
        return cidade;
    }

    public void setCidade(CidadeDTO cidade) {
        this.cidade = cidade;
    }
}