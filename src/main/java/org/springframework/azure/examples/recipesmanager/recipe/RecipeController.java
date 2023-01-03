package org.springframework.azure.examples.recipesmanager.recipe;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @GetMapping
    public void getRecipes() {

    }
}
