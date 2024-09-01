package br.example.com.beans;

import br.example.com.dto.EmpresaDTO;
import br.example.com.exceptions.ApiException;
import br.example.com.service.EmpresaService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

/**
 * Bean gerenciado pela JSF que fornece funcionalidades para buscar e salvar informações de empresas.
 */
@Named("indexBean")
@RequestScoped
public class IndexBean {

    // Campo para armazenar o CNPJ fornecido pelo usuário
    private String cnpj;

    // DTO para armazenar os dados da empresa
    private EmpresaDTO empresaDTO = new EmpresaDTO();

    // Serviço para interagir com a API externa
    private EmpresaService empresaService;

    /**
     * Construtor da classe. Injeta o serviço de empresa.
     * @param empresaService Serviço para operações relacionadas a empresas
     */
    @Inject
    public IndexBean(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    // Getter para o campo cnpj
    public String getCnpj() {
        return cnpj;
    }

    // Setter para o campo cnpj
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    // Getter para o DTO da empresa
    public EmpresaDTO getEmpresaDTO() {
        return empresaDTO;
    }

    // Setter para o DTO da empresa
    public void setEmpresaDTO(EmpresaDTO empresaDTO) {
        this.empresaDTO = empresaDTO;
    }

    /**
     * Método chamado para buscar informações de uma empresa usando o CNPJ fornecido.
     * Remove a formatação do CNPJ e chama o serviço para realizar a busca.
     */
    public void getEmpresaByCnpj() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            // Remove caracteres não numéricos do CNPJ
            String cnpjSemFormatacao = cnpj.replaceAll("[^0-9]", "");

            // Chama o serviço para buscar a empresa
            Optional<EmpresaDTO> optionalEmpresaDTO = empresaService.findCnpj(cnpjSemFormatacao);

            // Se a empresa for encontrada, atualiza o DTO e exibe uma mensagem de sucesso
            if (optionalEmpresaDTO.isPresent()) {
                this.empresaDTO = optionalEmpresaDTO.get();
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Empresa encontrada.", null));
            } else {
                // Se a empresa não for encontrada, exibe uma mensagem de erro
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Empresa não encontrada.", null));
            }
        } catch (ApiException e) {
            // Trata exceções e exibe uma mensagem de erro
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um erro ao buscar a empresa. Por favor, tente novamente.", "Erro: " + e.getMessage()));
        }
    }

    /**
     * Método chamado para salvar as informações de uma empresa.
     * Atualiza o DTO da empresa com a resposta do serviço e exibe uma mensagem apropriada.
     * @param empresaDTO DTO contendo as informações da empresa a ser salva
     */
    public void saveEmpresa(EmpresaDTO empresaDTO) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            // Exibe os dados da empresa no console para depuração
            System.out.println("EmpresaDTO: " + empresaDTO.toString());

            // Chama o serviço para salvar a empresa
            Optional<EmpresaDTO> optionalEmpresa = empresaService.saveCnpj(empresaDTO);

            // Se a empresa for salva com sucesso, atualiza o DTO e exibe uma mensagem de sucesso
            if (optionalEmpresa.isPresent()) {
                this.empresaDTO = optionalEmpresa.get();
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Empresa salva com sucesso.", "A Empresa foi salva com sucesso."));
            } else {
                // Se ocorrer um erro ao salvar a empresa, exibe uma mensagem de erro
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar empresa.", "Ocorreu um erro ao salvar a empresa. Por favor, tente novamente."));
            }
        } catch (ApiException e) {
            e.printStackTrace();
            // Trata exceções e exibe uma mensagem de erro
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um erro ao salvar a empresa. Por favor, tente novamente.", "Erro: " + e.getMessage()));
        }
    }
}
