package br.example.com.client;

import br.example.com.dto.EmpresaDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Interface que define os endpoints da API para interagir com as operações relacionadas à empresa.
 * Utiliza Retrofit para realizar as requisições HTTP.
 */
public interface IEmpresaRequest {

    /**
     * Faz uma requisição GET para buscar informações de uma empresa com base no CNPJ.
     *
     * @param cnpj O CNPJ da empresa a ser pesquisada, passado como parte da URL.
     * @return Um objeto `Call` que contém a resposta da API, que será um `EmpresaDTO` com os dados da empresa.
     */
    @GET("cnpj/{cnpj}")
    Call<EmpresaDTO> findCnpj(@Path("cnpj") String cnpj);

    /**
     * Faz uma requisição POST para salvar as informações de uma empresa.
     *
     * @param empresaDTO O DTO contendo as informações da empresa a ser salva, passado no corpo da requisição.
     * @return Um objeto `Call` que contém a resposta da API, que será um `EmpresaDTO` com os dados da empresa salva.
     */
    @POST("cnpj")
    Call<EmpresaDTO> saveCnpj(@Body EmpresaDTO empresaDTO);
}
