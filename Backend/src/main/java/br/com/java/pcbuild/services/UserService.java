package br.com.java.pcbuild.services;

import br.com.java.pcbuild.models.entities.User;
import br.com.java.pcbuild.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Integer userId, User updatedUser) {
        return userRepository.findById(userId)
                .map(userEntity -> {
                    modelMapper.map(updatedUser, userEntity);
                    return userRepository.save(userEntity);
                }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

}
