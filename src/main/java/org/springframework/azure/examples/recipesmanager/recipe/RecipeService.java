package org.springframework.azure.examples.recipesmanager.recipe;

import lombok.RequiredArgsConstructor;
import org.springframework.azure.examples.recipesmanager.client.EDAMAMWebClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final EDAMAMWebClient webClient;
    private final RecipeNoSQLRepository recipeNoSQLRepository;

    public Flux<Recipe> getRandomRecipeByType(String recipeType) {
        return webClient.getWebClient()
                .get()
                .uri(uriBuilder ->
                        uriBuilder.queryParam("type", "public")
                                .queryParam("beta", false)
                                .queryParam("app_id", "eac7d5bf")
                                .queryParam("app_key", "60fe990a64d53ec6b74b7b04ec3dc879")
                                .queryParam("q", recipeType)
                                .build())
                .retrieve()
                .bodyToFlux(Recipe.class);
    }

    public void saveRecipe(Recipe recipe) {
        Flux.just(recipe).flatMap(recipeNoSQLRepository::save);
    }

    public Mono<Recipe> getRecipeById(String recipeId) {
        return recipeNoSQLRepository.findById(recipeId);
    }
}
