package example.br.cnpjsearchapi.entities;

import example.br.cnpjsearchapi.dtos.EstabelecimentoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "endereco") // Mapeia a entidade para a tabela 'endereco' no banco de dados
public class Endereco {

    @Id // Define o campo 'id' como chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática do valor do 'id'
    private Long id;

    @Column(name = "tipo_logradouro", length = 50, nullable = false) // Configurações da coluna no banco de dados
    private String tipoLogradouro;

    @Column(name = "logradouro", length = 100, nullable = false)
    private String logradouro;

    @Column(name = "numero", length = 20)
    private String numero;

    @Column(name = "complemento", length = 100)
    private String complemento;

    @Column(name = "bairro", length = 50, nullable = false)
    private String bairro;

    @Column(name = "cep", length = 9, nullable = false)
    private String cep;

    @Column(name = "ddd", length = 2)
    private String ddd;

    @Column(name = "telefone", length = 9)
    private String telefone;

    @ManyToOne(cascade = {CascadeType.PERSIST})  // Relacionamento muitos-para-um com cascata de operações
    @JoinColumn(name = "cidade_id", referencedColumnName = "id")
    private Cidade cidade;

    // Construtor padrão
    public Endereco() {
    }

    // Construtor com parâmetros
    public Endereco(Long id, String tipoLogradouro, String logradouro, String numero, String complemento, String bairro, String cep, String ddd, String telefone, Cidade cidade) {
        this.id = id;
        this.tipoLogradouro = tipoLogradouro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.ddd = ddd;
        this.telefone = telefone;
        this.cidade = cidade;
    }

    public Endereco(EstabelecimentoDTO estabelecimento) {
        this.tipoLogradouro = estabelecimento.getTipoLogradouro();
        this.logradouro = estabelecimento.getLogradouro();
        this.numero = estabelecimento.getNumero();
        this.complemento = estabelecimento.getComplemento();
        this.bairro = estabelecimento.getBairro();
        this.cep = estabelecimento.getCep();
        this.ddd = estabelecimento.getDdd();
        this.telefone = estabelecimento.getTelefone();
        this.cidade = new Cidade(estabelecimento.getCidade());
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public EstabelecimentoDTO toEstabelecimentoDTO(Empresa empresa) {
        // Obtemos o endereço associado à empresa
        var endereco = empresa.getEndereco();

        // Construímos o EstabelecimentoDTO com base nos dados da empresa e do endereço
        return new EstabelecimentoDTO(
                empresa.getCnpj(),                            // CNPJ da empresa
                empresa.getSituacaoCadastral().getSituacao(),  // Situação cadastral da empresa
                empresa.getDataSituacaoCadastral().toString(), // Data da situação cadastral (convertida para String)
                endereco.getTipoLogradouro(),               // Tipo de logradouro do endereço
                endereco.getLogradouro(),                   // Logradouro do endereço
                endereco.getNumero(),                       // Número do endereço
                endereco.getComplemento(),                  // Complemento do endereço
                endereco.getCep(),                          // CEP do endereço
                endereco.getBairro(),                       // Bairro do endereço
                endereco.getDdd(),                          // DDD do telefone
                endereco.getTelefone(),                     // Telefone do endereço
                endereco.getCidade().toCidadeDTO()          // Conversão da cidade para CidadeDTO
        );
    }
}
