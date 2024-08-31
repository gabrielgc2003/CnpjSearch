package example.br.cnpjsearchapi.entities;

import example.br.cnpjsearchapi.dtos.CidadeDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "cidade") // Mapeia a entidade para a tabela 'cidade' no banco de dados
public class Cidade {

    @Id // Define o campo 'id' como chave primária da tabela
    private int id;

    @NotNull(message = "O nome da cidade não pode ser nulo.") // Validação para campo não nulo
    @Size(min = 1, max = 100, message = "O nome da cidade deve ter entre 1 e 100 caracteres.") // Validação para o intervalo de caracteres
    @Column(name = "nome", length = 100, nullable = false) // Configuração da coluna no banco, incluindo tamanho e não nulidade
    private String nome;

    // Construtores
    public Cidade() {
    }
    public Cidade(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Getters and Setters
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
