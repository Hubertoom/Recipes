package recipes.dto;

import recipes.model.Recipe;

public class Mapper {

    public static RecipeDTO mapRecipeToRecipeDTO(Recipe recipe) {
        return new RecipeDTO(recipe.getName(), recipe.getDescription(), recipe.getIngredients(), recipe.getDirections());
    }
}
