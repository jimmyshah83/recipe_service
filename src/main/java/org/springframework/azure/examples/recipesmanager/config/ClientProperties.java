package org.springframework.azure.examples.recipesmanager.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "client")
@Data
@Validated
public class ClientProperties {

    private String baseUrl;
    private String hostKey;
    private String hostValue;
}
