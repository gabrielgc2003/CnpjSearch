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

    private String nome;

    // Construtores
    public Cidade() {
    }
    public Cidade(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Cidade(CidadeDTO cidade) {
        this.id = cidade.getId();
        this.nome = cidade.getNome();
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


    public CidadeDTO toCidadeDTO() {
        return new CidadeDTO(this.id, this.nome);
    }
}
