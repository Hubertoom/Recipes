package recipes.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recipes.dto.UserDTO;
import recipes.service.RegistrationService;

import javax.validation.Valid;


@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/recipe")
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO) {
        registrationService.registerUser(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}