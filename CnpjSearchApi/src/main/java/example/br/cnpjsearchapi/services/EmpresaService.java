package example.br.cnpjsearchapi.services;

import example.br.cnpjsearchapi.dtos.CidadeDTO;
import example.br.cnpjsearchapi.dtos.requests.EmpresaRequestDTO;
import example.br.cnpjsearchapi.dtos.responses.EmpresaResponseDTO;
import example.br.cnpjsearchapi.entities.Cidade;
import example.br.cnpjsearchapi.entities.Empresa;
import example.br.cnpjsearchapi.entities.Endereco;
import example.br.cnpjsearchapi.enums.ClientRequestEnum;
import example.br.cnpjsearchapi.enums.SituacaoCadastralEnum;
import example.br.cnpjsearchapi.exceptions.ApiException;
import example.br.cnpjsearchapi.repositories.CidadeRepository;
import example.br.cnpjsearchapi.repositories.EmpresaRepository;
import example.br.cnpjsearchapi.utils.retrofit.config.RetrofitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.sql.Date;
import java.util.Optional;

@Service
public class EmpresaService {
    @Autowired
    public RateLimiterService rateLimiterService;

    @Autowired
    public EmpresaRepository empresaRepository;

    @Autowired
    public CidadeRepository cidadeRepository;

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
                throw new ApiException("Erro ao consultar CNPJ: CNPJ inválido ou não encontrado!");
            }
        } catch (IOException e) {
            throw new ApiException("Tempo esgotado: Nenhum retorno recebido do host!: " + e.getMessage());
        }
    }

    // Método para criar ou atualizar uma empresa
    public Optional<EmpresaResponseDTO> createCnpj(EmpresaRequestDTO empresaRequestDTO) {
        // Busca a empresa pelo CNPJ do estabelecimento
        Optional<Empresa> empresaOptional = empresaRepository.findByCnpj(empresaRequestDTO.getEstabelecimento().getCnpj());

        if (empresaOptional.isPresent()) {
            // Se a empresa existir, atualiza os campos necessários
            Empresa empresaExistente = empresaOptional.get();
            empresaExistente.setRazaoSocial(empresaRequestDTO.getRazaoSocial());
            empresaExistente.setDataSituacaoCadastral(Date.valueOf(empresaRequestDTO.getEstabelecimento().getDataSituacaoCadastral()));
            empresaExistente.setSituacaoCadastral(SituacaoCadastralEnum.fromSituacao(empresaRequestDTO.getEstabelecimento().getSituacaoCadastral()));

            // Atualiza o endereço e verifica a cidade
            Endereco enderecoExistente = empresaExistente.getEndereco();
            enderecoExistente.setTipoLogradouro(empresaRequestDTO.getEstabelecimento().getTipoLogradouro());
            enderecoExistente.setLogradouro(empresaRequestDTO.getEstabelecimento().getLogradouro());
            enderecoExistente.setNumero(empresaRequestDTO.getEstabelecimento().getNumero());
            enderecoExistente.setComplemento(empresaRequestDTO.getEstabelecimento().getComplemento());
            enderecoExistente.setBairro(empresaRequestDTO.getEstabelecimento().getBairro());
            enderecoExistente.setCep(empresaRequestDTO.getEstabelecimento().getCep());
            enderecoExistente.setDdd(empresaRequestDTO.getEstabelecimento().getDdd());
            enderecoExistente.setTelefone(empresaRequestDTO.getEstabelecimento().getTelefone());

            // Gerencia a cidade para evitar duplicações
            Cidade cidade = gerenciarCidade(empresaRequestDTO.getEstabelecimento().getCidade());
            enderecoExistente.setCidade(cidade);

            empresaExistente.setDataCadastro(new Date(System.currentTimeMillis()));
            // Salva a empresa atualizada no banco de dados
            empresaRepository.save(empresaExistente);

            // Retorna o DTO de resposta com os dados atualizados da empresa
            return Optional.of(new EmpresaResponseDTO(empresaExistente.getRazaoSocial(), empresaExistente.getEndereco().toEstabelecimentoDTO(empresaExistente)));
        } else {
            // Se a empresa não existir, cria um novo objeto de Empresa usando o request DTO
            Empresa novaEmpresa = new Empresa(empresaRequestDTO);
            novaEmpresa.setDataCadastro(new Date(System.currentTimeMillis()));

            // Gerencia a cidade para evitar duplicações antes de setar no endereço da nova empresa
            Cidade cidade = gerenciarCidade(empresaRequestDTO.getEstabelecimento().getCidade());
            novaEmpresa.getEndereco().setCidade(cidade);

            // Salva a nova empresa no banco de dados
            empresaRepository.save(novaEmpresa);

            // Retorna o DTO de resposta com os dados da nova empresa
            return Optional.of(new EmpresaResponseDTO(novaEmpresa.getRazaoSocial(), novaEmpresa.getEndereco().toEstabelecimentoDTO(novaEmpresa)));
        }
    }

    private Cidade gerenciarCidade(CidadeDTO cidadeDTO) {
        // Busca a cidade existente pelo ID ou outro critério de unicidade
        return cidadeRepository.findByNome(cidadeDTO.getNome())
                .orElseGet(() -> {
                    // Se não existir, cria uma nova cidade e persiste
                    Cidade novaCidade = new Cidade();
                    novaCidade.setId(cidadeDTO.getId());
                    novaCidade.setNome(cidadeDTO.getNome());
                    return cidadeRepository.save(novaCidade);
                });
    }
}
