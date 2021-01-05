package com.sk.karte.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class KorisnikServisConfiguration {

    @Bean
    public RestTemplate korisnikServisRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:8083/korisnicki-servis/api"));
        return restTemplate;
    }
}
