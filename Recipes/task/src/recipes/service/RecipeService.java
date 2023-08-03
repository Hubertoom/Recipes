package recipes.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import recipes.dto.Mapper;
import recipes.dto.RecipeDTO;
import recipes.exceptions.RecipeNotFoundException;
import recipes.model.Recipe;
import recipes.repository.RecipeRepository;

@AllArgsConstructor
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeDTO getRecipe(int id) {
        try {
            return Mapper.mapRecipeToRecipeDTO(
                            recipeRepository.getRecipes().get(id)
            );
        } catch (IndexOutOfBoundsException e) {
            throw new RecipeNotFoundException("Recipe with id " + id + " not found!");
        }
    }

    public void addRecipe(Recipe recipe) {
        recipeRepository.addRecipe(recipe);
    }
}