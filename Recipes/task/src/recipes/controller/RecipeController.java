package recipes.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipes.dto.RecipeDTO;
import recipes.model.IdResponse;
import recipes.model.Recipe;
import recipes.service.RecipeService;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getRecipe(@PathVariable Integer id) {
        return ResponseEntity
                .ok(recipeService.getRecipe(id));
    }

    @PostMapping("/new")
    public ResponseEntity<IdResponse> addRecipe(@RequestBody Recipe recipe) {
        return ResponseEntity
                .ok(recipeService.addRecipe(recipe));
    }
}
