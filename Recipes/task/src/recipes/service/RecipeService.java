package recipes.service;

import org.springframework.stereotype.Service;
import recipes.model.Recipe;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {

    private final List<Recipe> recipeList = new ArrayList<>();

    public Recipe getRecipe(int id) {
        return recipeList.get(id);
    }

    public void addRecipe(Recipe recipe) {
        recipeList.add(0, recipe);
    }
}