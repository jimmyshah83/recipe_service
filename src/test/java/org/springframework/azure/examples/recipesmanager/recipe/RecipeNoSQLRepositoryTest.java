package org.springframework.azure.examples.recipesmanager.recipe;

import com.azure.cosmos.CosmosAsyncClient;
import com.azure.cosmos.CosmosClientBuilder;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.CosmosDBEmulatorContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.io.FileOutputStream;
import java.security.KeyStore;

@Testcontainers
@SpringBootTest
@Disabled
class RecipeNoSQLRepositoryTest {

    @Container
    private static final CosmosDBEmulatorContainer cosmosDBEmulatorContainer = new CosmosDBEmulatorContainer(DockerImageName
            .parse("mcr.microsoft.com/cosmosdb/linux/azure-cosmos-emulator:latest"));

    private static CosmosAsyncClient cosmosAsyncClient;

    private int recipeId = 1234;

    @Autowired
    private RecipeNoSQLRepository recipeNoSQLRepository;

    @BeforeAll
    public static void init() throws Exception {
        KeyStore keyStore = cosmosDBEmulatorContainer.buildNewKeyStore();
        char[] pwdArray = "keystorePass".toCharArray();
        keyStore.load(null, pwdArray);
        try (FileOutputStream fos = new FileOutputStream("azure-cosmos-emulator.jks")) {
            keyStore.store(fos, pwdArray);
        }

        System.setProperty("javax.net.ssl.trustStore", "azure-cosmos-emulator.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", cosmosDBEmulatorContainer.getEmulatorKey());
        System.setProperty("javax.net.ssl.trustStoreType", "PKCS12");

        cosmosAsyncClient = new CosmosClientBuilder()
                .gatewayMode()
                .endpointDiscoveryEnabled(false)
                .endpoint(cosmosDBEmulatorContainer.getEmulatorEndpoint())
                .key(cosmosDBEmulatorContainer.getEmulatorKey())
                .buildAsyncClient();
    }

    @BeforeEach
    public void setupTests() {
        recipeNoSQLRepository.save(RecipeEntity.builder()
                .name("")
                .description("Join Tasty & First We Feast for Eat Your Feed Fest, our first-ever food festival, November 19 and 20, 2022 in Long Beach, California! Want to attend the fest as our guest? Leave a comment on this recipe letting us know what you’d like to try at Eat Your Feed Fest and fill out our <a href=\"https://docs.google.com/forms/d/e/1FAIpQLSeJkPFqODH2GRe_gb_2Bs0MuuMFLKFRjiWmmctlX6UyeJXNEg/viewform?usp=sf_link\">official entry form</a>. The winner will receive two General Admission 2-Day Tickets to ComplexCon. Winners will be selected at random and notified by November 10, 2022.")
                .recipeId(recipeId)
                .slug("eat-your-feed-fest-pizza")
                .build());
    }

    @Test
    public void should_find_recipe() {
        RecipeEntity recipe = recipeNoSQLRepository.findById(recipeId).block();
        assert recipe != null;
        Assertions.assertEquals(recipe.getId(), recipeId);
    }
}