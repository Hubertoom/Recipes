package recipes.persitance;

import lombok.Getter;
import org.springframework.stereotype.Component;
import recipes.model.Recipe;
import recipes.repository.RecipeRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class RecipesFakeRepository implements RecipeRepository {
    private final List<Recipe> recipeList = new ArrayList<>();

    @Override
    public List<Recipe> getRecipes() {
        return recipeList;
    }

    @Override
    public void addRecipe(Recipe recipe) {
        recipeList.add(recipe);
    }
}
