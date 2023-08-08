package recipes.dto;

import recipes.model.Recipe;

public class Mapper {

    public static recipes.dto.RecipeDTO mapRecipeToRecipeDTO(Recipe recipe) {
        return new recipes.dto.RecipeDTO(
                recipe.getName(),
                recipe.getCategory(),
                recipe.getDate(),
                recipe.getDescription(),
                recipe.getIngredients(),
                recipe.getDirections()
        );
    }

    public static RecipeIdDTO mapRecipeToRecipeIdDTO(Recipe recipe) {
        return new RecipeIdDTO(recipe.getId());
    }

    public static Recipe mapRecipeDtoToRecipe(RecipeDTO recipeDTO) {
        return new Recipe(
                recipeDTO.name(),
                recipeDTO.description(),
                recipeDTO.ingredients(),
                recipeDTO.directions(),
                recipeDTO.category());
    }
}