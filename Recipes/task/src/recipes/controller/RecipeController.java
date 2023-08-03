package recipes.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipes.dto.RecipeDTO;
import recipes.model.Recipe;
import recipes.service.RecipeService;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getRecipe(@PathVariable int id) {
        return ResponseEntity
                .ok(recipeService.getRecipe(id));
    }

    @PostMapping("/new")
    public ResponseEntity<RecipeDTO> addRecipe(@RequestBody Recipe recipe) {
        recipeService.addRecipe(recipe);
        return ResponseEntity
                .ok(recipeService.getRecipe(0));
    }
}
