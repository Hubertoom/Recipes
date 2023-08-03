package recipes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerApi {

    @ExceptionHandler(value = RecipeNotFoundException.class)
    public ResponseEntity<Object> handleRecipeNotFoundException(
            RecipeNotFoundException e
    ) {
        ApiException apiException = new ApiException(
                e.getMessage()
        );

        return new ResponseEntity<>(
                apiException,
                HttpStatus.NOT_FOUND
        );
    }
}
