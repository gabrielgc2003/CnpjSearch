package example.br.cnpjsearchapi.utils.retrofit.config;

import example.br.cnpjsearchapi.client.IEmpresaRequest;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Configuração do Retrofit para realizar requisições HTTP.
 */
public class RetrofitConfig {

    private Retrofit retrofit; // Instância do Retrofit para fazer as requisições
    private static final String BASE_URL = "https://publica.cnpj.ws/"; // URL base para a API

    /**
     * Construtor da classe. Configura o Retrofit com a URL base e o conversor JSON.
     */
    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL) // Define a URL base da API
                .addConverterFactory(JacksonConverterFactory.create()) // Configura o conversor JSON Jackson
                .build(); // Constrói a instância do Retrofit
    }

    /**
     * Cria e retorna uma instância da interface de requisições da empresa.
     *
     * @return uma instância de IEmpresaRequest para fazer as chamadas à API
     */
    public IEmpresaRequest cnpjRequest() {
        return this.retrofit.create(IEmpresaRequest.class); // Cria uma implementação da interface IEmpresaRequest
    }
}
