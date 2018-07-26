package br.edu.tasima.aw.talg.instaritter.client;

import br.edu.tasima.aw.talg.instaritter.client.service.PhotoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class InstaritterClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstaritterClientApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public PhotoService photoService() {
        return new PhotoService();
    }
}
