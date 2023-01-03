package org.springframework.azure.examples.recipesmanager.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cosmos")
@Data
public class CosmosNoSQLProperties {

    private String uri;
    private String key;
    private String secondaryKey;
    private boolean queryMetricsEnabled;
}
