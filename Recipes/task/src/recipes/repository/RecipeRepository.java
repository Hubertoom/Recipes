package recipes.repository;

import recipes.model.Recipe;

import java.util.List;

public interface RecipeRepository {

    List<Recipe> getRecipes();

    void addRecipe(Recipe recipe);
}
