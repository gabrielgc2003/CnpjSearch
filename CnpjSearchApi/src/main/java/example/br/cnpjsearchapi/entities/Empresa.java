package example.br.cnpjsearchapi.entities;

import example.br.cnpjsearchapi.dtos.EmpresaResponseDTO;
import example.br.cnpjsearchapi.enums.SituacaoCadastralEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;

@Entity
@Table(name = "empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera automaticamente o valor do ID
    private Long id;

    @NotNull(message = "O CNPJ não pode ser nulo.") // Validação para campo não nulo
    @Size(min = 14, max = 14, message = "O CNPJ deve ter exatamente 14 caracteres.") // Validação para o tamanho exato de 14 caracteres
    @Column(name = "cnpj", length = 14, nullable = false, unique = true) // Configuração da coluna no banco
    private String cnpj;

    @NotNull(message = "A razão social não pode ser nula.") // Validação para campo não nulo
    @Size(min = 1, max = 255, message = "A razão social deve ter entre 1 e 255 caracteres.") // Validação para o intervalo de caracteres
    @Column(name = "razao_social", length = 255, nullable = false) // Configuração da coluna no banco
    private String razaoSocial;

    @OneToOne(cascade = CascadeType.ALL) // Relacionamento um-para-um com cascata de operações
    @JoinColumn(name = "endereco_id", referencedColumnName = "id") // Define a chave estrangeira para a tabela Endereco
    private Endereco endereco;

    @NotNull(message = "A situação cadastral não pode ser nula.") // Validação para campo não nulo
    @Enumerated(EnumType.STRING) // Armazena o valor da enum como String no banco de dados
    @Column(name = "situacao_cadastral", nullable = false) // Configuração da coluna no banco
    private SituacaoCadastralEnum situacaoCadastral;

    @Column(name = "data_situacao_cadastral") // Configuração da coluna no banco
    private Timestamp dataSituacaoCadastral;

    // Construtor padrão
    public Empresa() {
    }

    // Construtor com parâmetros
    public Empresa(Long id, String cnpj, String razaoSocial, Endereco endereco, SituacaoCadastralEnum situacaoCadastral, Timestamp dataSituacaoCadastral) {
        this.id = id;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.endereco = endereco;
        this.situacaoCadastral = situacaoCadastral;
        this.dataSituacaoCadastral = dataSituacaoCadastral;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public SituacaoCadastralEnum getSituacaoCadastral() {
        return situacaoCadastral;
    }

    public void setSituacaoCadastral(SituacaoCadastralEnum situacaoCadastral) {
        this.situacaoCadastral = situacaoCadastral;
    }

    public Timestamp getDataSituacaoCadastral() {
        return dataSituacaoCadastral;
    }

    public void setDataSituacaoCadastral(Timestamp dataSituacaoCadastral) {
        this.dataSituacaoCadastral = dataSituacaoCadastral;
    }


}