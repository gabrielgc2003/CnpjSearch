package example.br.cnpjsearchapi.exceptions;

// Exceção personalizada para a API
public class ApiException extends Exception{
    public ApiException(String msg) {
        super(msg);
    }
}
