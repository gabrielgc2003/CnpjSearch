package example.br.cnpjsearchapi.entities;

import example.br.cnpjsearchapi.dtos.EstabelecimentoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "endereco") // Mapeia a entidade para a tabela 'endereco' no banco de dados
public class Endereco {

    @Id // Define o campo 'id' como chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática do valor do 'id'
    private Long id;

    @NotNull(message = "O tipo de logradouro não pode ser nulo.") // Validação para campo não nulo
    @Size(max = 50, message = "O tipo de logradouro deve ter no máximo 50 caracteres.") // Define o tamanho máximo do campo
    @Column(name = "tipo_logradouro", length = 50, nullable = false) // Configurações da coluna no banco de dados
    private String tipoLogradouro;

    @NotNull(message = "O logradouro não pode ser nulo.")
    @Size(max = 100, message = "O logradouro deve ter no máximo 100 caracteres.")
    @Column(name = "logradouro", length = 100, nullable = false)
    private String logradouro;

    @Size(max = 20, message = "O número deve ter no máximo 20 caracteres.")
    @Column(name = "numero", length = 20)
    private String numero;

    @Size(max = 100, message = "O complemento deve ter no máximo 100 caracteres.")
    @Column(name = "complemento", length = 100)
    private String complemento;

    @NotNull(message = "O bairro não pode ser nulo.")
    @Size(max = 50, message = "O bairro deve ter no máximo 50 caracteres.")
    @Column(name = "bairro", length = 50, nullable = false)
    private String bairro;

    @NotNull(message = "O CEP não pode ser nulo.")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 99999-999.") // Validação de formato
    @Column(name = "cep", length = 9, nullable = false)
    private String cep;

    @Size(max = 2, message = "O DDD deve ter no máximo 2 caracteres.")
    @Column(name = "ddd", length = 2)
    private String ddd;

    @Size(max = 9, message = "O telefone deve ter no máximo 9 caracteres.")
    @Column(name = "telefone", length = 9)
    private String telefone;

    @OneToOne // Relacionamento um para um com a entidade 'Cidade'
    @JoinColumn(name = "cidade_id", referencedColumnName = "id") // Configuração da chave estrangeira
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


}
