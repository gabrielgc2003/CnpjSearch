package br.example.com.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

   public static <T> String toJson(T value) throws JsonProcessingException {
      return new ObjectMapper()
              .writeValueAsString(value);
   }
   public static <T> T fromJson(String value,final Class<T> type) throws IOException {
      return new ObjectMapper()
              .readValue(value, type);
   }
}
