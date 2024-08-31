package br.example.com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
   private String CNPJ;
   private String companyName;
   private String city;
   private String registrationStatus;
   private Date registrationDate;
}
