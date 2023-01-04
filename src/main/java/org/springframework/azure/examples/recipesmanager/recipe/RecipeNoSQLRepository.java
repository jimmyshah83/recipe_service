package org.springframework.azure.examples.recipesmanager.recipe;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeNoSQLRepository extends ReactiveCosmosRepository<Recipe, String> {
}
