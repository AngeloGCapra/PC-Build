package br.com.java.pcbuild.services;

import br.com.java.pcbuild.models.entities.UserBuild;
import br.com.java.pcbuild.repositories.UserBuildRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserBuildService {

    private final ModelMapper modelMapper;
    private final UserBuildRepository userBuildRepository;

    public List<UserBuild> findAllUserBuilds() {
        return userBuildRepository.findAll();
    }

    public Optional<UserBuild> findUserBuildById(Integer userBuildId) {
        return userBuildRepository.findById(userBuildId);
    }

    public UserBuild createUserBuild(UserBuild userBuild) {
        return userBuildRepository.save(userBuild);
    }

    public UserBuild updateUserBuild(Integer userBuildId, UserBuild updatedUserBuild) {
        return userBuildRepository.findById(userBuildId)
                .map(existingUserBuildEntity -> {
                    modelMapper.map(updatedUserBuild, existingUserBuildEntity);
                    return userBuildRepository.save(existingUserBuildEntity);
                }).orElseThrow(() -> new RuntimeException("User build not found"));
    }

    public void deleteUserBuild(Integer userBuildId) {
        userBuildRepository.deleteById(userBuildId);
    }

}
