package example.br.cnpjsearchapi.entities;

import example.br.cnpjsearchapi.dtos.requests.EmpresaRequestDTO;
import example.br.cnpjsearchapi.enums.SituacaoCadastralEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Date;

/**
 * Representa uma entidade Empresa no banco de dados.
 */
@Entity
@Table(name = "empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera automaticamente o valor do ID
    private Long id;

    @Column(name = "cnpj", length = 14, nullable = false, unique = true) // Configuração da coluna no banco
    private String cnpj;

    @Column(name = "razao_social", length = 255, nullable = false) // Configuração da coluna no banco
    private String razaoSocial;

    @OneToOne(cascade = CascadeType.PERSIST) // Relacionamento um-para-um com cascata de operações
    @JoinColumn(name = "endereco_id", referencedColumnName = "id") // Define a chave estrangeira para a tabela Endereco
    private Endereco endereco;

    @Enumerated(EnumType.STRING) // Armazena o valor da enum como String no banco de dados
    @Column(name = "situacao_cadastral", nullable = false) // Configuração da coluna no banco
    private SituacaoCadastralEnum situacaoCadastral;

    @Column(name = "data_situacao_cadastral") // Configuração da coluna no banco
    private Date dataSituacaoCadastral;

    @Column(name = "data_cadastro", nullable = false) // Configuração da coluna no banco
    private Date dataCadastro;

    // Construtor padrão
    public Empresa() {
    }

    // Construtor com parâmetros
    public Empresa(Long id, String cnpj, String razaoSocial, Endereco endereco, SituacaoCadastralEnum situacaoCadastral, Date dataSituacaoCadastral) {
        this.id = id;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.endereco = endereco;
        this.situacaoCadastral = situacaoCadastral;
        this.dataSituacaoCadastral = dataSituacaoCadastral;
    }

    // Construtor a partir do DTO de requisição
    public Empresa(EmpresaRequestDTO empresaRequestDTO) {
        this.razaoSocial = empresaRequestDTO.getRazaoSocial();
        this.cnpj = empresaRequestDTO.getEstabelecimento().getCnpj();
        this.situacaoCadastral = SituacaoCadastralEnum.fromSituacao(empresaRequestDTO.getEstabelecimento().getSituacaoCadastral());
        this.dataSituacaoCadastral = Date.valueOf(empresaRequestDTO.getEstabelecimento().getDataSituacaoCadastral());
        this.endereco = new Endereco(empresaRequestDTO.getEstabelecimento());
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

    public Date getDataSituacaoCadastral() {
        return dataSituacaoCadastral;
    }

    public void setDataSituacaoCadastral(Date dataSituacaoCadastral) {
        this.dataSituacaoCadastral = dataSituacaoCadastral;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
