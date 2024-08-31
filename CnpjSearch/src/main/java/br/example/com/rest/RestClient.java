package br.example.com.rest;

import br.example.com.bean.dto.CompanyDto;
import br.example.com.utils.JsonUtil;


import javax.enterprise.context.ApplicationScoped;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@ApplicationScoped
public class RestClient {
   private final String apiUrl = System.getenv("BASE_API_URL") + "company/";
   public CompanyDto getCompany(String cnpj) {
      if(cnpj == null || cnpj.isEmpty()) return null;
      try {
         URL url = new URL(apiUrl + cnpj.trim());
         HttpURLConnection connection = (HttpURLConnection) url.openConnection();
         connection.setRequestMethod("GET");
         BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
         String line;
         StringBuilder response = new StringBuilder();
         while ((line = reader.readLine()) != null) {
            response.append(line);
         }
         reader.close();
         connection.disconnect();

         return JsonUtil.fromJson(response.toString(), CompanyDto.class);
      } catch (IOException e) {
         throw new RuntimeException(e.getMessage());
      }
   }
   public boolean saveCompany(CompanyDto companyDto){
      try {
         URL url = new URL(apiUrl + "save");
         String jsonData = JsonUtil.toJson(companyDto);
         HttpURLConnection connection = (HttpURLConnection) url.openConnection();
         connection.setRequestMethod("POST");
         connection.setRequestProperty("Content-Type", "application/json");
         connection.setDoOutput(true);

         try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
            outputStream.writeBytes(jsonData);
            outputStream.flush();
         }

         int responseCode = connection.getResponseCode();
         connection.disconnect();
         return responseCode == HttpURLConnection.HTTP_OK;
      }catch (IOException e) {
         throw new RuntimeException(e.getMessage());
      }


   }
}
