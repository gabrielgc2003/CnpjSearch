package br.example.com.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

// Classe de comunicação Message
public class MessageDTO {
    @JsonProperty("message")
    String message;

    public MessageDTO() {
    }

    public MessageDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
