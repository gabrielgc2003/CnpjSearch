package br.example.com.utils.retrofit;

import br.example.com.client.IEmpresaRequest;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Configuração do Retrofit para interação com a API externa.
 * Configura o Retrofit com a URL base da API e o conversor de JSON.
 */
public class RetrofitConfig {
    private Retrofit retrofit;
    private static final String BASE_URL = System.getenv("BASE_API_URL");

    /**
     * Construtor que inicializa o Retrofit com as configurações necessárias.
     * Define a URL base da API e o conversor de JSON para objetos Java.
     */
    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)  // Define a URL base da API obtida das variáveis de ambiente
                .addConverterFactory(JacksonConverterFactory.create())  // Adiciona o conversor Jackson para processar JSON
                .build();  // Constrói a instância do Retrofit
    }

    /**
     * Cria uma instância do cliente para a interface `IEmpresaRequest`.
     *
     * @return Uma instância de `IEmpresaRequest` configurada para fazer chamadas à API.
     */
    public IEmpresaRequest cnpjRequest() {
        return this.retrofit.create(IEmpresaRequest.class);  // Cria e retorna o cliente para interagir com a API
    }
}