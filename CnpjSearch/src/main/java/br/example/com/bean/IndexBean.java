package br.example.com.bean;

import br.example.com.bean.dto.CompanyDto;
import br.example.com.rest.RestClient;
import br.example.com.utils.CnpjUtil;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named("indexBean")
@RequestScoped
@Getter
@Setter
public class IndexBean {
   private String CNPJ;
   private RestClient restClient;
   private SessionBean sessionBean;
   private CompanyDto company;
   @Inject
   public IndexBean(RestClient restClient, SessionBean sessionBean) {
      this.restClient = restClient;
      this.sessionBean = sessionBean;
   }
   public void getCompanyByCnpj(){
      System.out.println("Getting info [ Company CNPJ: " + CNPJ + " ]");
      company = restClient.getCompany(CnpjUtil.onlyDigits(this.CNPJ));
      if (company != null){
         sessionBean.setCompany(company);
         redirect();
      }
   }
   public void redirect() {
      ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
      try {
         externalContext.redirect("company-info.xhtml");
      } catch (IOException e) {
         e.printStackTrace();
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro ao redirecionar."));
      }
   }
}
