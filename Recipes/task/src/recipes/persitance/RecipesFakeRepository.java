package recipes.persitance;

import lombok.Getter;
import org.springframework.stereotype.Component;
import recipes.model.Recipe;
import recipes.repository.RecipeRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@Getter
public class RecipesFakeRepository implements RecipeRepository {
    private final Map<Integer, Recipe> recipeList = new HashMap<>();
    private int sequenceId = 1;
    @Override
    public Optional<Recipe> getRecipeById(Integer id) {
        return Optional.ofNullable(recipeList.get(id));
    }

    @Override
    public int addRecipe(Recipe recipe) {
        recipeList.put(sequenceId, recipe);
        return sequenceId++;
    }
}
