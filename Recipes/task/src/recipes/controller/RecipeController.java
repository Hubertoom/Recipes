package recipes.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import recipes.dto.RecipeDTO;
import recipes.dto.RecipeIdDTO;
import recipes.model.User;
import recipes.service.RecipeService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getRecipe(@PathVariable Long id) {

        return ResponseEntity
                .ok(recipeService.getRecipe(id));
    }

    @PostMapping("/new")
    public ResponseEntity<RecipeIdDTO> addRecipe(
            @Valid @RequestBody RecipeDTO recipeDTO,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity
                .ok(recipeService.addRecipe(recipeDTO, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RecipeIdDTO> deleteRecipe(@PathVariable Long id,
                                                    @AuthenticationPrincipal User user
    ) {
        RecipeIdDTO dto = recipeService.deleteRecipeById(id, user);
        return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecipe(@Valid @RequestBody RecipeDTO recipeDTO,
                                          @PathVariable Long id,
                                          @AuthenticationPrincipal User user
    ) {
        recipeService.updateRecipe(id, recipeDTO, user);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<RecipeDTO>> searchRecipe(
            @RequestParam(required = false) Optional<String> name,
            @RequestParam(required = false) Optional<String> category
    ) {
        if (name.isPresent() && category.isPresent()
                || name.isEmpty() && category.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        return ResponseEntity
                .ok(recipeService.searchByCategoryOrName(name, category));
    }

}
