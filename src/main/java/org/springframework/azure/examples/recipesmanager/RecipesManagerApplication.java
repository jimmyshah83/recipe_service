package org.springframework.azure.examples.recipesmanager;

import org.springframework.azure.examples.recipesmanager.config.ClientProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ClientProperties.class})
public class RecipesManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipesManagerApplication.class, args);
    }

}
