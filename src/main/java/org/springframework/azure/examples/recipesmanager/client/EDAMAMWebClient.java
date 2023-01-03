package org.springframework.azure.examples.recipesmanager.client;

import lombok.RequiredArgsConstructor;
import org.springframework.azure.examples.recipesmanager.config.ClientProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class EDAMAMWebClient {

    private final ClientProperties clientProperties;
    private WebClient webClient;

    public WebClient getWebClient() {
        if (null != webClient) {
            return webClient;
        }
        return WebClient.create(clientProperties.getBaseUrl());
    }
}
