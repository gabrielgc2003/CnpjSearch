package example.br.cnpjsearchapi.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true) // Ignora propriedades desconhecidas
public class CidadeDTO {
    private int id;
    @NotNull(message = "O nome da cidade não pode ser nulo.") // Validação para campo não nulo
    @Size(min = 1, max = 100, message = "O nome da cidade deve ter entre 1 e 100 caracteres.") // Validação para o intervalo de caracteres
    @Column(name = "nome", length = 100, nullable = false) // Configuração da coluna no banco, incluindo tamanho e não nulidade
    private String nome;

    // Construtor padrão
    public CidadeDTO() {
    }

    // Construtor com parâmetros
    public CidadeDTO(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
