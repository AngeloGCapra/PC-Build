package br.com.java.pcbuild.controllers;

import br.com.java.pcbuild.models.entities.UserBuild;
import br.com.java.pcbuild.services.UserBuildService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user-build")
public class UserBuildController {

    private final UserBuildService userBuildService;

    @GetMapping
    public ResponseEntity<List<UserBuild>> getAllUserBuilds() {
        List<UserBuild> userBuilds = userBuildService.findAllUserBuilds();
        return ResponseEntity.ok(userBuilds);
    }

    @GetMapping("/{userBuildId}")
    public ResponseEntity<Optional<UserBuild>> getUserBuildById(@PathVariable("userBuildId") Integer userBuildId) {
        Optional<UserBuild> userBuild = userBuildService.findUserBuildById(userBuildId);
        return ResponseEntity.ok(userBuild);
    }

    @PostMapping
    public ResponseEntity<UserBuild> createUserBuild(@RequestBody UserBuild userBuild) {
        UserBuild newUserBuild = userBuildService.createUserBuild(userBuild);
        return new ResponseEntity<>(newUserBuild, HttpStatus.CREATED);
    }

    @PutMapping("/{userBuildId}")
    public ResponseEntity<UserBuild> updateUserBuild(@PathVariable("userBuildId") Integer userBuildId,
                                                     @RequestBody UserBuild updatedUserBuild) {
        UserBuild userBuild = userBuildService.updateUserBuild(userBuildId, updatedUserBuild);
        return ResponseEntity.ok(userBuild);
    }

    @DeleteMapping("/{userBuildId}")
    public ResponseEntity<Void> deleteUserBuild(@PathVariable("userBuildId") Integer userBuildId) {
        userBuildService.deleteUserBuild(userBuildId);
        return ResponseEntity.noContent().build();
    }

}
