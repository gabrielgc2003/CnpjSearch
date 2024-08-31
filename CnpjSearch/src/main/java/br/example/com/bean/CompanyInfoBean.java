package br.example.com.bean;

import br.example.com.bean.dto.CompanyDto;
import br.example.com.rest.RestClient;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named("companyInfoBean")
@RequestScoped
@Getter
@Setter
public class CompanyInfoBean {
   CompanyDto company;
   private RestClient restClient;
   private SessionBean sessionBean;
   @Inject
   public CompanyInfoBean(RestClient restClient,SessionBean sessionBean) {
      this.restClient = restClient;
      this.sessionBean = sessionBean;
      this.company = sessionBean.getCompany();
   }
   public void redirect() {
      ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
      try {
         externalContext.redirect("index.xhtml");
      } catch (IOException e) {
         e.printStackTrace();
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro ao redirecionar."));
      }
   }
   public void save(){
      if((company.getAddress() == null || company.getAddress().isEmpty()) && ((company.getPhone() == null || company.getPhone().isEmpty()))){
         return;
      }
      restClient.saveCompany(company);
   }
}
