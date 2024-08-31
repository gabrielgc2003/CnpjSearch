package example.br.cnpjsearchapi.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

@JsonIgnoreProperties(ignoreUnknown = true) // Ignora propriedades desconhecidas
public class EstabelecimentoDTO {

    @JsonProperty("cnpj")
    @Size(min = 14, max = 14, message = "O CNPJ deve ter exatamente 14 caracteres.") // Validação para o tamanho exato de 14 caracteres
    @NotBlank(message = "CNPJ é obrigatório.")
    @CNPJ(message = "CNPJ inválido.")
    private String cnpj;

    @JsonProperty("situacao_cadastral")
    @NotNull(message = "A situação cadastral não pode ser nula.") // Validação para campo não nulo
    private String situacaoCadastral;

    @JsonProperty("data_situacao_cadastral")
    @NotNull(message = "A data da situação cadastral não pode ser nula.") // Validação para campo não nulo
    private String dataSituacaoCadastral;

    @JsonProperty("tipo_logradouro")
    @NotNull(message = "O tipo de logradouro não pode ser nulo.") // Validação para campo não nulo
    @Size(max = 50, message = "O tipo de logradouro deve ter no máximo 50 caracteres.") // Define o tamanho máximo do campo
    private String tipoLogradouro;

    @JsonProperty("logradouro")
    @NotNull(message = "O logradouro não pode ser nulo.")
    @Size(max = 100, message = "O logradouro deve ter no máximo 100 caracteres.")
    private String logradouro;

    @JsonProperty("numero")
    @Size(max = 20, message = "O número deve ter no máximo 20 caracteres.")
    private String numero;

    @JsonProperty("complemento")
    @Size(max = 100, message = "O complemento deve ter no máximo 100 caracteres.")
    private String complemento;

    @JsonProperty("cep")
    @NotNull(message = "O CEP não pode ser nulo.")
    private String cep;

    @JsonProperty("bairro")
    @NotNull(message = "O bairro não pode ser nulo.")
    @Size(max = 50, message = "O bairro deve ter no máximo 50 caracteres.")
    private String bairro;

    @JsonProperty("ddd1")
    @Size(max = 2, message = "O DDD deve ter no máximo 2 caracteres.")
    private String ddd;

    @JsonProperty("telefone1")
    @Size(max = 9, message = "O telefone deve ter no máximo 9 caracteres.")
    private String telefone;

    @JsonProperty("cidade")
    @Valid
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