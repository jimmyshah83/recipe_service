package org.springframework.azure.examples.recipesmanager.recipe;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Container(containerName = "recipe")
@Data
@Builder
public class Recipe {

    @Id
    private String id;

    private String description;

    private String name;

    private String slug;

    @PartitionKey
    private String country;

    private Integer servings;
}
