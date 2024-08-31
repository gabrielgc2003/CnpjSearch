package br.example.com.bean.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyDto {
      private String CNPJ;
      private String companyName;
      private String city;
      private String registrationStatus;
      @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
      @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
      @JsonSerialize(using = DateSerializer.class)
      private Date registrationDate;
      private String phone;
      private String address;
}
