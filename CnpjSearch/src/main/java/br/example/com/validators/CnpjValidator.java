package br.example.com.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validador customizado para CNPJ.
 * Esta classe valida o formato e a integridade de um número de CNPJ fornecido.
 */
@FacesValidator("CnpjValidator")
public class CnpjValidator implements Validator {

   /**
    * Valida o valor do CNPJ fornecido.
    *
    * @param context O contexto de Faces que é usado para enviar mensagens de erro.
    * @param component O componente UI ao qual o valor pertence.
    * @param value O valor a ser validado (deve ser um CNPJ).
    *
    * @throws ValidatorException Se o valor do CNPJ for inválido, lança uma exceção de validação.
    */
   @Override
   public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
      String cnpj = (String) value;

      // Verifica se o CNPJ é válido
      if (!isValid(cnpj)) {
         throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "CNPJ inválido", "O CNPJ informado é inválido!"));
      }

      // Verifica se o CNPJ é nulo ou vazio
      if (cnpj == null || cnpj.isEmpty()) {
         throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "CNPJ inválido", "O CNPJ informado não pode ser nulo!"));
      }
   }

   /**
    * Verifica se o CNPJ é válido.
    *
    * @param cnpj O CNPJ a ser validado.
    * @return true se o CNPJ for válido, false caso contrário.
    */
   public static boolean isValid(String cnpj) {
      // Remove caracteres não numéricos do CNPJ
      cnpj = cnpj.replaceAll("[^\\d]", "");

      // Verifica se o comprimento do CNPJ é 14 dígitos
      if (cnpj.length() != 14) {
         return false;
      }

      // Verifica se todos os dígitos são iguais (ex.: "11111111111111")
      if (cnpj.chars().distinct().count() == 1) {
         return false;
      }

      // Validação dos dígitos verificadores usando os multiplicadores
      int[] multiplicadores1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
      int[] multiplicadores2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

      return validaDigito(cnpj, multiplicadores1, 12) && validaDigito(cnpj, multiplicadores2, 13);
   }

   /**
    * Valida um dígito específico do CNPJ com base nos multiplicadores fornecidos.
    *
    * @param cnpj O CNPJ a ser validado.
    * @param multiplicadores Os multiplicadores usados na validação dos dígitos.
    * @param posicao A posição do dígito a ser validado (12 ou 13).
    * @return true se o dígito for válido, false caso contrário.
    */
   private static boolean validaDigito(String cnpj, int[] multiplicadores, int posicao) {
      int soma = 0;
      for (int i = 0; i < multiplicadores.length; i++) {
         soma += Character.getNumericValue(cnpj.charAt(i)) * multiplicadores[i];
      }
      // Calcula o dígito verificador
      int digito = (soma % 11 < 2) ? 0 : 11 - (soma % 11);
      return digito == Character.getNumericValue(cnpj.charAt(posicao));
   }
}
