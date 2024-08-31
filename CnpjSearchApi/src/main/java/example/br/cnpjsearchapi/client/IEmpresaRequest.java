package example.br.cnpjsearchapi.client;

import example.br.cnpjsearchapi.dtos.responses.EmpresaResponseDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IEmpresaRequest {
    @GET("cnpj/{cnpj}")
    Call<EmpresaResponseDTO> findCnpj(@Path("cnpj") String cnpj);
}
