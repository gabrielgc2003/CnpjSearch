package example.br.cnpjsearchapi.client;

import example.br.cnpjsearchapi.dtos.responses.EmpresaResponseDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Interface para definir as requisições HTTP para a API de busca de CNPJ.
 * Utiliza Retrofit para comunicação com a API REST.
 */
public interface IEmpresaRequest {

    /**
     * Realiza uma requisição GET para buscar informações de uma empresa pelo CNPJ.
     *
     * @param cnpj O CNPJ da empresa a ser consultado.
     * @return Um objeto Call que encapsula a resposta da API contendo dados da empresa.
     */
    @GET("cnpj/{cnpj}")
    Call<EmpresaResponseDTO> findCnpj(@Path("cnpj") String cnpj);
}
