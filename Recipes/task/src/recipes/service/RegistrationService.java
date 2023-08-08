package recipes.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import recipes.dto.Mapper;
import recipes.dto.UserDTO;
import recipes.exceptions.UserExistException;
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

        userDTO.setPassword(encoder.encode(userDTO.getPassword()));

        userRepository.save(Mapper.mapUserDtoToUser(userDTO));
    }
}
