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

/**
 * Serviço para gerenciar operações relacionadas à empresa, como busca e criação/atualização de CNPJ.
 */
@Service
public class EmpresaService {

    @Autowired
    private RateLimiterService rateLimiterService; // Serviço para gerenciar limites de requisição

    @Autowired
    private EmpresaRepository empresaRepository; // Repositório para acesso a dados de empresa

    @Autowired
    private CidadeRepository cidadeRepository; // Repositório para acesso a dados de cidade

    /**
     * Busca informações de uma empresa pelo CNPJ.
     *
     * @param cnpj o CNPJ da empresa
     * @return um Optional contendo os dados da empresa, se encontrados
     * @throws ApiException se houver erro na consulta
     */
    public Optional<EmpresaResponseDTO> getCnpj(String cnpj) throws ApiException {
        // Verifica se o limite de requisição foi atingido
        if (!rateLimiterService.canMakeRequest(ClientRequestEnum.CNPJ_SEARCH.name())) {
            throw new ApiException("Limite de consultas para CNPJ_SEARCH atingido. Tente novamente mais tarde.");
        }

        // Busca a empresa no banco de dados
        Optional<Empresa> empresa = empresaRepository.findByCnpj(cnpj);

        // Faz a requisição para o serviço externo
        Call<EmpresaResponseDTO> call = new RetrofitConfig().cnpjRequest().findCnpj(cnpj);
        try {
            Response<EmpresaResponseDTO> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                EmpresaResponseDTO responseDTO = response.body();

                // Se a empresa já existe no banco, atualiza os dados da resposta com informações existentes
                empresa.ifPresent(emp -> updateEndereco(responseDTO, emp));

                return Optional.of(responseDTO);
            } else {
                throw new ApiException("Erro ao consultar CNPJ: CNPJ inválido ou não encontrado!");
            }
        } catch (IOException e) {
            throw new ApiException("Tempo esgotado: Nenhum retorno recebido do host!: " + e.getMessage());
        }
    }

    /**
     * Cria ou atualiza uma empresa com base nos dados fornecidos.
     *
     * @param empresaRequestDTO os dados da empresa a serem criados ou atualizados
     * @return um Optional contendo os dados da empresa, se criada ou atualizada
     */
    public Optional<EmpresaResponseDTO> createCnpj(EmpresaRequestDTO empresaRequestDTO) {
        // Busca a empresa pelo CNPJ
        Optional<Empresa> empresaOptional = empresaRepository.findByCnpj(empresaRequestDTO.getEstabelecimento().getCnpj());

        if (empresaOptional.isPresent()) {
            // Atualiza a empresa existente
            return Optional.of(updateEmpresa(empresaRequestDTO, empresaOptional.get()));
        } else {
            // Cria uma nova empresa
            return Optional.of(createNewEmpresa(empresaRequestDTO));
        }
    }

    /**
     * Atualiza os dados da resposta da empresa com as informações existentes.
     *
     * @param responseDTO o DTO da resposta da empresa
     * @param empresa a entidade da empresa existente
     */
    private void updateEndereco(EmpresaResponseDTO responseDTO, Empresa empresa) {
        Endereco endereco = empresa.getEndereco();
        responseDTO.getEstabelecimentoDTO().setTipoLogradouro(endereco.getTipoLogradouro());
        responseDTO.getEstabelecimentoDTO().setLogradouro(endereco.getLogradouro());
        responseDTO.getEstabelecimentoDTO().setNumero(endereco.getNumero());
        responseDTO.getEstabelecimentoDTO().setComplemento(endereco.getComplemento());
        responseDTO.getEstabelecimentoDTO().setBairro(endereco.getBairro());
        responseDTO.getEstabelecimentoDTO().setCep(endereco.getCep());
        responseDTO.getEstabelecimentoDTO().setDdd(endereco.getDdd());
        responseDTO.getEstabelecimentoDTO().setTelefone(endereco.getTelefone());
    }

    /**
     * Atualiza uma empresa existente com os dados fornecidos.
     *
     * @param empresaRequestDTO os dados da empresa a serem atualizados
     * @param empresa a entidade da empresa existente
     * @return o DTO da resposta da empresa atualizada
     */
    private EmpresaResponseDTO updateEmpresa(EmpresaRequestDTO empresaRequestDTO, Empresa empresa) {
        empresa.setRazaoSocial(empresaRequestDTO.getRazaoSocial());
        empresa.setDataSituacaoCadastral(Date.valueOf(empresaRequestDTO.getEstabelecimento().getDataSituacaoCadastral()));
        empresa.setSituacaoCadastral(SituacaoCadastralEnum.fromSituacao(empresaRequestDTO.getEstabelecimento().getSituacaoCadastral()));

        Endereco endereco = empresa.getEndereco();
        alteraEndereco(empresaRequestDTO, endereco);

        // Gerencia a cidade e atualiza no endereço
        Cidade cidade = gerenciarCidade(empresaRequestDTO.getEstabelecimento().getCidade());
        endereco.setCidade(cidade);

        empresa.setDataCadastro(new Date(System.currentTimeMillis()));
        empresaRepository.save(empresa);

        return new EmpresaResponseDTO(empresa.getRazaoSocial(), endereco.toEstabelecimentoDTO(empresa));
    }

    /**
     * Cria uma nova empresa com os dados fornecidos.
     *
     * @param empresaRequestDTO os dados da empresa a serem criados
     * @return o DTO da resposta da nova empresa criada
     */
    private EmpresaResponseDTO createNewEmpresa(EmpresaRequestDTO empresaRequestDTO) {
        Empresa novaEmpresa = new Empresa(empresaRequestDTO);
        novaEmpresa.setDataCadastro(new Date(System.currentTimeMillis()));

        Cidade cidade = gerenciarCidade(empresaRequestDTO.getEstabelecimento().getCidade());
        novaEmpresa.getEndereco().setCidade(cidade);

        empresaRepository.save(novaEmpresa);

        return new EmpresaResponseDTO(novaEmpresa.getRazaoSocial(), novaEmpresa.getEndereco().toEstabelecimentoDTO(novaEmpresa));
    }

    /**
     * Gerencia a criação ou busca de uma cidade.
     *
     * @param cidadeDTO os dados da cidade
     * @return a cidade encontrada ou criada
     */
    private Cidade gerenciarCidade(CidadeDTO cidadeDTO) {
        return cidadeRepository.findByNome(cidadeDTO.getNome())
                .orElseGet(() -> {
                    Cidade novaCidade = new Cidade();
                    novaCidade.setId(cidadeDTO.getId());
                    novaCidade.setNome(cidadeDTO.getNome());
                    return cidadeRepository.save(novaCidade);
                });
    }

    /**
     * Atualiza os dados do endereço com base no DTO fornecido.
     *
     * @param empresaRequestDTO os dados do endereço
     * @param endereco o endereço a ser atualizado
     */
    private void alteraEndereco(EmpresaRequestDTO empresaRequestDTO, Endereco endereco) {
        endereco.setTipoLogradouro(empresaRequestDTO.getEstabelecimento().getTipoLogradouro());
        endereco.setLogradouro(empresaRequestDTO.getEstabelecimento().getLogradouro());
        endereco.setNumero(empresaRequestDTO.getEstabelecimento().getNumero());
        endereco.setComplemento(empresaRequestDTO.getEstabelecimento().getComplemento());
        endereco.setBairro(empresaRequestDTO.getEstabelecimento().getBairro());
        endereco.setCep(empresaRequestDTO.getEstabelecimento().getCep());
        endereco.setDdd(empresaRequestDTO.getEstabelecimento().getDdd());
        endereco.setTelefone(empresaRequestDTO.getEstabelecimento().getTelefone());
    }
}
