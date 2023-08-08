package recipes.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import recipes.dto.Mapper;
import recipes.dto.RecipeDTO;
import recipes.dto.RecipeIdDTO;
import recipes.exceptions.NotOwnerException;
import recipes.exceptions.RecipeNotFoundException;
import recipes.model.Recipe;
import recipes.model.User;
import recipes.repository.RecipeRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeDTO getRecipe(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(RecipeNotFoundException::new);

        return Mapper.mapRecipeToRecipeDTO(recipe);
    }

    @Transactional
    public RecipeIdDTO addRecipe(RecipeDTO recipeDTO, User user) {
        Recipe savedRecipe = Mapper.mapRecipeDtoToRecipe(recipeDTO);
        savedRecipe.setUser(user);
        recipeRepository.save(savedRecipe);
        return Mapper.mapRecipeToRecipeIdDTO(savedRecipe);
    }

    @Transactional
    public RecipeIdDTO deleteRecipeById(Long id, User user) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(RecipeNotFoundException::new);

        checkIsOwner(recipe, user);

        recipeRepository.delete(recipe);
        return Mapper.mapRecipeToRecipeIdDTO(recipe);
    }

    @Transactional
    public void updateRecipe(Long id, RecipeDTO recipeDTO, User user) {
        if (!recipeRepository.existsById(id)) {
            throw new RecipeNotFoundException();
        }

        recipeRepository.findById(id)
                .ifPresent(oldRecipe -> {
                            Recipe recipe = Mapper.mapRecipeDtoToRecipe(recipeDTO);
                            checkIsOwner(recipe, user);
                            recipe.setId(oldRecipe.getId());
                            recipeRepository.save(recipe);
                        }
                );
    }

    public List<RecipeDTO> searchByCategoryOrName(Optional<String> name, Optional<String> category) {
        return name.map(n -> recipeRepository.findByNameContainsIgnoreCaseOrderByDateDesc(n).stream()
                        .map(Mapper::mapRecipeToRecipeDTO)
                        .toList())
                .orElseGet(() -> recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category.get()).stream()
                        .map(Mapper::mapRecipeToRecipeDTO)
                        .toList());
    }

    private boolean checkIsOwner(Recipe recipe, User user) {
        if (!recipe.getUser().getEmail().equals(user.getEmail())) {
            throw new NotOwnerException();
        }
        return true;
    }
}