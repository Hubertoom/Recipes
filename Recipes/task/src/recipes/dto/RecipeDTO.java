package recipes.dto;

public record RecipeDTO(String name,
                        String description,
                        String ingredients,
                        String directions) {
}