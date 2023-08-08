package recipes.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

public record RecipeDTO(@NotBlank String name,
                        @NotBlank String category,
                        LocalDateTime date,
                        @NotBlank String description,
                        @NotEmpty List<String> ingredients,
                        @NotEmpty List<String> directions) {
}