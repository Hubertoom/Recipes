package recipes.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import recipes.dto.UserDTO;
import recipes.exceptions.UserExistException;
import recipes.model.User;
import recipes.repository.UserRepository;

@AllArgsConstructor
@Service
public class RegistrationService {

    private UserRepository userRepository;
    private PasswordEncoder encoder;

    public void registerUser(UserDTO userDTO) {
        if (userRepository.findUserByEmail(userDTO.getEmail()).isPresent()) {
            throw new UserExistException();
        }

        User user = new User(
                userDTO.getEmail().split("@")[0],
                userDTO.getEmail(),
                encoder.encode(userDTO.getPassword()),
                "ROLE_USER"
        );

        userRepository.save(user);
    }
}