package org.springframework.azure.examples.recipesmanager.recipe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;

@SpringBootTest
class RecipeServiceTest {

    @Autowired
    private RecipeService recipeService;

    @Test
    public void testSave() {
        recipeService.saveRecipe(RecipeEntity.builder()
                .name("test name")
                .description("test description")
                .recipeId(1234)
                .slug("test slug")
                .servings(8)
                .createdDate(OffsetDateTime.now())
                .build());

        RecipeEntity recipeEntity = recipeService.getRecipeById(1234).block();
        assert recipeEntity != null;
        Assertions.assertEquals(recipeEntity.getServings(), 8);
    }
}