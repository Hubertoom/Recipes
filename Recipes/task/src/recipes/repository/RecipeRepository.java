package recipes.repository;

import recipes.model.Recipe;

import java.util.Optional;

public interface RecipeRepository {

    Optional<Recipe> getRecipeById(Integer id);

    int addRecipe(Recipe recipe);
}
