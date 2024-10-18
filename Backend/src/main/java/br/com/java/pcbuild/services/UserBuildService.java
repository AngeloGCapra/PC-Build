package br.com.java.pcbuild.services;

import br.com.java.pcbuild.models.entities.UserBuild;
import br.com.java.pcbuild.repositories.UserBuildRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserBuildService {

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
                .map(existingUserBuild -> {
                    existingUserBuild.setTotalPrice(updatedUserBuild.getTotalPrice());
                    existingUserBuild.setUsageType(updatedUserBuild.getUsageType());
                    existingUserBuild.setUser(updatedUserBuild.getUser());
                    existingUserBuild.setCpu(updatedUserBuild.getCpu());
                    existingUserBuild.setGpu(updatedUserBuild.getGpu());
                    existingUserBuild.setMotherboard(updatedUserBuild.getMotherboard());
                    existingUserBuild.setRam(updatedUserBuild.getRam());
                    existingUserBuild.setStorage(updatedUserBuild.getStorage());
                    existingUserBuild.setPowerSupply(updatedUserBuild.getPowerSupply());
                    existingUserBuild.setPcCase(updatedUserBuild.getPcCase());
                    existingUserBuild.setCpuCooler(updatedUserBuild.getCpuCooler());

                    return userBuildRepository.save(existingUserBuild);
                }).orElseThrow(() -> new RuntimeException("User build not found"));
    }

    public void deleteUserBuild(Integer userBuildId) {
        userBuildRepository.deleteById(userBuildId);
    }

}
