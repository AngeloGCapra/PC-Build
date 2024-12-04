package br.com.java.pcbuild.controllers;

import br.com.java.pcbuild.enums.UsageTypesEnum;
import br.com.java.pcbuild.models.entities.UserBuild;
import br.com.java.pcbuild.services.UserBuildService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user-build")
public class UserBuildController {

    private final UserBuildService userBuildService;

    @GetMapping("/getAllUserBuilds")
    public ResponseEntity<List<UserBuild>> getAllUserBuilds() {
        List<UserBuild> userBuilds = userBuildService.findAllUserBuilds();
        return ResponseEntity.ok(userBuilds);
    }

    @GetMapping("/getUserBuildById/{userBuildId}")
    public ResponseEntity<Optional<UserBuild>> getUserBuildById(@PathVariable("userBuildId") Long userBuildId) {
        Optional<UserBuild> userBuild = userBuildService.findUserBuildById(userBuildId);
        return ResponseEntity.ok(userBuild);
    }

    @PostMapping("/generateBuild")
    public ResponseEntity<Optional<UserBuild>> generateBuild(@RequestHeader(value = "userId", required = false) Long userId,
                                                             @RequestParam(value = "budget") BigDecimal budget,
                                                             @RequestParam(value = "usageType") UsageTypesEnum usageType) {
        Optional<UserBuild> newUserBuild = userBuildService.generateBuildWithOptimization(userId, budget, usageType);
        return new ResponseEntity<>(newUserBuild, HttpStatus.CREATED);
    }

    @PutMapping("/updateUserBuild/{userBuildId}")
    public ResponseEntity<UserBuild> updateUserBuild(@PathVariable("userBuildId") Long userBuildId,
                                                     @RequestBody UserBuild updatedUserBuild) {
        UserBuild userBuild = userBuildService.updateUserBuild(userBuildId, updatedUserBuild);
        return ResponseEntity.ok(userBuild);
    }

    @DeleteMapping("/deleteUserBuild/{userBuildId}")
    public ResponseEntity<Void> deleteUserBuild(@PathVariable("userBuildId") Long userBuildId) {
        userBuildService.deleteUserBuild(userBuildId);
        return ResponseEntity.noContent().build();
    }

}
