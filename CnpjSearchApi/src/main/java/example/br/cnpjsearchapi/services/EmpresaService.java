package example.br.cnpjsearchapi.services;

import example.br.cnpjsearchapi.dtos.EmpresaResponseDTO;
import example.br.cnpjsearchapi.entities.Empresa;
import example.br.cnpjsearchapi.enums.ClientRequestEnum;
import example.br.cnpjsearchapi.exceptions.ApiException;
import example.br.cnpjsearchapi.repositories.EmpresaRepository;
import example.br.cnpjsearchapi.utils.retrofit.config.RetrofitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Optional;

@Service
public class EmpresaService {
    @Autowired
    public RateLimiterService rateLimiterService;

    @Autowired
    public EmpresaRepository empresaRepository;

    public Optional<EmpresaResponseDTO> getCnpj(String cnpj) throws ApiException {
        //Busca CNPJ no banco para preencher dados já existentes caso necessário alterações
        Optional<Empresa> empresa = empresaRepository.findByCnpj(cnpj);

        // Verificar se a requisição pode ser feita
        if (!rateLimiterService.canMakeRequest(ClientRequestEnum.CNPJ_SEARCH.name())) {
            throw new ApiException("Limite de consultas para CNPJ_SEARCH atingido. Tente novamente mais tarde.");
        }
        Call<EmpresaResponseDTO> call = new RetrofitConfig().cnpjRequest().findCnpj(cnpj);
        try {
            Response<EmpresaResponseDTO> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                Optional<EmpresaResponseDTO> empresaResponse = Optional.of(response.body());

                if (empresa.isPresent()) {
                    empresaResponse.get().getEstabelecimentoDTO().setTipoLogradouro(empresa.get().getEndereco().getTipoLogradouro());
                    empresaResponse.get().getEstabelecimentoDTO().setLogradouro(empresa.get().getEndereco().getLogradouro());
                    empresaResponse.get().getEstabelecimentoDTO().setNumero(empresa.get().getEndereco().getNumero());
                    empresaResponse.get().getEstabelecimentoDTO().setComplemento(empresa.get().getEndereco().getComplemento());
                    empresaResponse.get().getEstabelecimentoDTO().setBairro(empresa.get().getEndereco().getBairro());
                    empresaResponse.get().getEstabelecimentoDTO().setCep(empresa.get().getEndereco().getCep());
                    empresaResponse.get().getEstabelecimentoDTO().setDdd(empresa.get().getEndereco().getDdd());
                    empresaResponse.get().getEstabelecimentoDTO().setTelefone(empresa.get().getEndereco().getTelefone());
                }

                return empresaResponse;
            } else {
                throw new ApiException("Erro ao consultar CNPJ: " + response.errorBody().string());
            }
        } catch (IOException e) {
            throw new ApiException("Tempo esgotado: Nenhum retorno recebido do host!");
        }
    }
}
