package example.br.cnpjsearchapi.utils.retrofit.config;

import example.br.cnpjsearchapi.client.IEmpresaRequest;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {
    private Retrofit retrofit;
    private static final String BASE_URL = "https://publica.cnpj.ws/";

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

    }
    public IEmpresaRequest cnpjRequest(){
        return this.retrofit.create(IEmpresaRequest.class);
    }
}
