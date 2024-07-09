package musinsa;

import musinsa.application.port.out.ProductRepositoryPort;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MusinsaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusinsaApplication.class, args);
    }


    @Bean(initMethod = "init")
    public InitDataProcess initDataProcessBean(ProductRepositoryPort productRepositoryPort) {
        return new InitDataProcess(productRepositoryPort);
    }
}


