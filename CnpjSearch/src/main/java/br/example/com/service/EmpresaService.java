package br.example.com.service;

import br.example.com.dto.EmpresaDTO;
import br.example.com.dto.MessageDTO;
import br.example.com.exceptions.ApiException;
import br.example.com.utils.retrofit.RetrofitConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import retrofit2.Call;
import retrofit2.Response;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.util.Optional;

/**
 * Serviço responsável por interagir com a API externa para realizar operações relacionadas à empresa.
 * Utiliza Retrofit para enviar e receber dados da API.
 */
@ApplicationScoped
public class EmpresaService {

    /**
     * Busca informações de uma empresa com base no CNPJ fornecido.
     *
     * @param cnpj O CNPJ da empresa a ser consultada.
     * @return Um `Optional` contendo o `EmpresaDTO` com as informações da empresa, se encontrada.
     * @throws ApiException Se ocorrer um erro durante a chamada à API ou ao processar a resposta.
     */
    public Optional<EmpresaDTO> findCnpj(String cnpj) throws ApiException {
        // Cria a chamada Retrofit para buscar a empresa pelo CNPJ
        Call<EmpresaDTO> call = new RetrofitConfig().cnpjRequest().findCnpj(cnpj);
        try {
            // Executa a chamada e obtém a resposta
            Response<EmpresaDTO> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                // Retorna o corpo da resposta encapsulado em um Optional
                return Optional.of(response.body());
            } else {
                // Se a resposta não for bem-sucedida, extrai a mensagem de erro
                String errorBody = response.errorBody().string();
                ObjectMapper objectMapper = new ObjectMapper();
                MessageDTO error = objectMapper.readValue(errorBody, MessageDTO.class);
                throw new ApiException(error.getMessage());
            }
        } catch (IOException e) {
            // Lança uma exceção personalizada em caso de erro de IO
            throw new ApiException("Tempo esgotado: Nenhum retorno recebido do host!: " + e.getMessage());
        }
    }

    /**
     * Salva as informações de uma empresa fornecida.
     *
     * @param empresaDTO O DTO contendo as informações da empresa a ser salva.
     * @return Um `Optional` contendo o `EmpresaDTO` com as informações da empresa salva, se bem-sucedido.
     * @throws ApiException Se ocorrer um erro durante a chamada à API ou ao processar a resposta.
     */
    public Optional<EmpresaDTO> saveCnpj(EmpresaDTO empresaDTO) throws ApiException {
        // Cria a chamada Retrofit para salvar as informações da empresa
        Call<EmpresaDTO> call = new RetrofitConfig().cnpjRequest().saveCnpj(empresaDTO);
        try {
            // Executa a chamada e obtém a resposta
            Response<EmpresaDTO> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                // Retorna o corpo da resposta encapsulado em um Optional
                return Optional.of(response.body());
            } else {
                // Se a resposta não for bem-sucedida, extrai a mensagem de erro
                String errorBody = response.errorBody().string();
                ObjectMapper objectMapper = new ObjectMapper();
                MessageDTO error = objectMapper.readValue(errorBody, MessageDTO.class);
                throw new ApiException(error.getMessage());
            }
        } catch (IOException e) {
            // Lança uma exceção personalizada em caso de erro de IO
            throw new ApiException("Tempo esgotado: Nenhum retorno recebido do host!: " + e.getMessage());
        }
    }
}
