package org.springframework.azure.examples.recipesmanager.recipe;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.OffsetDateTime;

@Container(containerName = "recipe", ru = "400")
@Data
@Builder
public class RecipeEntity {

    @Id
    private String id;

    private Integer recipeId;

    private String description;

    private String name;

    private String slug;

    @PartitionKey
    private Integer servings;

    private OffsetDateTime createdDate;
}
