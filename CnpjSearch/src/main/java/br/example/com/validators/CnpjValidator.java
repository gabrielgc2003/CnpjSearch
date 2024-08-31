package br.example.com.validators;

import br.example.com.utils.CnpjUtil;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


public class CnpjValidator implements Validator {
   @Override
   public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
      String cnpj = (String) value;
      if(!CnpjUtil.isValid(cnpj)){
         throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cnpj Invalido", "Cnpj Invalido"));
      }
   }
}
