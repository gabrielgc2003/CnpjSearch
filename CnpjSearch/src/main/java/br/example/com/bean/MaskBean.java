package br.example.com.bean;

import lombok.Getter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named("maskBean")
@Getter
public class MaskBean {
   private final String CNPJ_MASK = "99.999.999/9999-99";
   private final String PHONE_MASK = "(99)99999-9999";
}
