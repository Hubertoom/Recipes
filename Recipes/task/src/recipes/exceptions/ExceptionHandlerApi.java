package recipes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerApi {

    @ExceptionHandler(value = RecipeNotFoundException.class)
    public ResponseEntity<Object> handleRecipeNotFoundException() {
        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(value = UserExistException.class)
    public ResponseEntity<Object> handleUserExistException() {
        return new ResponseEntity<>(
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = NotOwnerException.class)
    public ResponseEntity<Object> handleNotOwnerException() {
        return new ResponseEntity<>(
                HttpStatus.FORBIDDEN
        );
    }
}