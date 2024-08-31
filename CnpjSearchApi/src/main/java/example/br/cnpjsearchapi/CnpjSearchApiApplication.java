package example.br.cnpjsearchapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CnpjSearchApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CnpjSearchApiApplication.class, args);
    }

}
