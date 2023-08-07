package recipes.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import recipes.dto.Mapper;
import recipes.dto.RecipeDTO;
import recipes.exceptions.RecipeNotFoundException;
import recipes.model.IdResponse;
import recipes.model.Recipe;
import recipes.repository.RecipeRepository;

import java.util.Optional;

@AllArgsConstructor
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeDTO getRecipe(int id) {
        Optional<Recipe> optionalRecipe = recipeRepository.getRecipeById(id);

        if (optionalRecipe.isEmpty()) {
            throw new RecipeNotFoundException("Recipe with id " + id + " not found!");
        }

        return optionalRecipe
                .map(Mapper::mapRecipeToRecipeDTO)
                .get();
    }

    public IdResponse addRecipe(Recipe recipe) {
        int id = recipeRepository.addRecipe(recipe);
        return new IdResponse(id);
    }
}