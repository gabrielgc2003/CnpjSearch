package br.example.com.utils;

public class CnpjUtil {
   private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
   public static boolean isValid(String cnpj){return isValidCNPJ(cnpj);}
   private static int calcularDigito(String str, int[] peso) {
      int soma = 0;
      for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
         digito = Integer.parseInt(str.substring(indice,indice+1));
         soma += digito*peso[peso.length-str.length()+indice];
      }
      soma = 11 - soma % 11;
      return soma > 9 ? 0 : soma;
   }

   private static boolean isValidCNPJ(String cnpj) {
      if (cnpj == null || cnpj.isEmpty()) return false;
      cnpj = onlyDigits(cnpj);
      if (cnpj.length()!=14) return false;

      int digito1 = calcularDigito(cnpj.substring(0,12), pesoCNPJ);
      int digito2 = calcularDigito(cnpj.substring(0,12) + digito1, pesoCNPJ);
      return cnpj.equals(cnpj.substring(0,12) + digito1 + digito2);
   }
   public static String onlyDigits(String cnpj){
      return cnpj.trim().replace(".", "").replace("-", "").replace("/","");
   }
}
