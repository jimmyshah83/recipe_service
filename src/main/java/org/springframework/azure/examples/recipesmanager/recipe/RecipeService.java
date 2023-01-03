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

    public Flux<RecipeEntity> getRandomRecipeByType(String recipeType) {
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
                .bodyToFlux(RecipeEntity.class);
    }

    public Mono<RecipeEntity> saveRecipe(RecipeEntity recipe) {
        return recipeNoSQLRepository.save(recipe);
    }

    public Mono<RecipeEntity> getRecipeById(Integer id) {
        return recipeNoSQLRepository.findById(id);
    }
}
