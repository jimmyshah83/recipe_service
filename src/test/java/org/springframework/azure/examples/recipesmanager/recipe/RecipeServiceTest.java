package org.springframework.azure.examples.recipesmanager.recipe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RecipeServiceTest {

    private String recipeId = "5678";

    @Autowired
    private RecipeService recipeService;

    @Test
    public void testSave() {
        recipeService.saveRecipe(
                Recipe.builder()
                        .name("test name")
                        .description("test description")
                        .id(recipeId)
                        .slug("test slug")
                        .servings(8)
                        .country("US")
                        .build());

        Recipe recipe = recipeService.getRecipeById("1234").block();
        assert recipe != null;
        Assertions.assertEquals(recipe.getServings(), 8);
    }
}