package recipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Recipe")
@Table(name = "recipe")
public class Recipe {

    @Id
    @Column(
            name = "id",
            nullable = false
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @Column
    private String name;

    @NotBlank
    @Column
    private String description;

    @NotEmpty
    @ElementCollection
    private List<String> ingredients;

    @NotEmpty
    @ElementCollection
    private List<String> directions;

    @NotBlank
    @Column
    private String category;

    @UpdateTimestamp
    LocalDateTime date;

    public Recipe(String name, String description, List<String> ingredients, List<String> directions, String category) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
        this.category = category;
    }
}

