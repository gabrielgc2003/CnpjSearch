package br.example.com.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

// Classe de comunicação Cidade
public class CidadeDTO {
    @JsonProperty("id")
    private int id;
    @JsonProperty("nome")
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

    @Override
    public String toString() {
        return "CidadeDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
