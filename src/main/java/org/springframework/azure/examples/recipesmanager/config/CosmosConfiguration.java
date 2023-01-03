package org.springframework.azure.examples.recipesmanager.config;

import com.azure.cosmos.CosmosClientBuilder;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.config.CosmosConfig;
import com.azure.spring.data.cosmos.repository.config.EnableReactiveCosmosRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
@EnableReactiveCosmosRepositories(basePackages = "org.springframework.azure.examples.recipesmanager.recipe")
@RequiredArgsConstructor
public class CosmosConfiguration extends AbstractCosmosConfiguration {

    private final CosmosNoSQLProperties cosmosNoSQLProperties;

    @Bean
    public CosmosClientBuilder cosmosClientBuilder() {
        return new CosmosClientBuilder()
                .endpoint(cosmosNoSQLProperties.getUri())
                .key(cosmosNoSQLProperties.getKey());
    }

    @Bean
    public CosmosConfig cosmosConfig() {
        return CosmosConfig.builder()
                .enableQueryMetrics(cosmosNoSQLProperties.isQueryMetricsEnabled())
                .build();
    }

    @Override
    protected String getDatabaseName() {
        return "recipedb";
    }
}
