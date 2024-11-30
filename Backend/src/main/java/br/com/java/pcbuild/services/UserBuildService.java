package br.com.java.pcbuild.services;

import br.com.java.pcbuild.Utils.Component;
import br.com.java.pcbuild.enums.UsageTypesEnum;
import br.com.java.pcbuild.models.entities.*;
import br.com.java.pcbuild.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserBuildService {

    private final ModelMapper modelMapper;
    private final UserBuildRepository userBuildRepository;
    private final ProcessorRepository processorRepository;
    private final GraphicsCardRepository graphicsCardRepository;
    private final MotherboardRepository motherboardRepository;
    private final RamModuleRepository ramModuleRepository;
    private final StorageRepository storageRepository;
    private final PowerSupplyRepository powerSupplyRepository;
    private final CaseRepository caseRepository;
    private final CpuCoolerRepository cpuCoolerRepository;
    private final UserRepository userRepository;

    public List<UserBuild> findAllUserBuilds() {
        return userBuildRepository.findAll();
    }

    public Optional<UserBuild> findUserBuildById(Long userBuildId) {
        return userBuildRepository.findById(userBuildId);
    }

    public UserBuild createUserBuild(UserBuild userBuild) {
        return userBuildRepository.save(userBuild);
    }

    public UserBuild updateUserBuild(Long userBuildId, UserBuild updatedUserBuild) {
        return userBuildRepository.findById(userBuildId)
                .map(existingUserBuildEntity -> {
                    modelMapper.map(updatedUserBuild, existingUserBuildEntity);
                    return userBuildRepository.save(existingUserBuildEntity);
                }).orElseThrow(() -> new RuntimeException("User build not found"));
    }

    public void deleteUserBuild(Long userBuildId) {
        userBuildRepository.deleteById(userBuildId);
    }

    public Optional<UserBuild> generateBuild(Long userId, BigDecimal budget, UsageTypesEnum usageType) {
        log.info("Generating build: User: {}, Budget: {}, Usage Type: {}", userId, budget, usageType);

        // Initialize components
        Processor selectedCpu = new Processor();
        GraphicsCard selectedGpu = new GraphicsCard();
        Motherboard selectedMotherboard = new Motherboard();
        RamModule selectedRam = new RamModule();
        CpuCooler selectedCpuCooler = new CpuCooler();
        PowerSupply selectedPowerSupply = new PowerSupply();
        Storage selectedStorage = new Storage();
        Case selectedCase = new Case();

        BigDecimal remainingBudget = budget;

        // 1. Selecting CPU and GPU
        if (usageType.equals(UsageTypesEnum.GAMES)) { // Prioritizing GPU first
            selectedGpu = selectGraphicsCard(remainingBudget);
            remainingBudget = subtractPrice(selectedGpu, remainingBudget);

            selectedCpu = selectProcessor(remainingBudget, usageType);
            remainingBudget = subtractPrice(selectedCpu, remainingBudget);
        } else if (usageType.equals(UsageTypesEnum.WORK)) { // Prioritizing CPU first
            selectedCpu = selectProcessor(remainingBudget, usageType);
            remainingBudget = subtractPrice(selectedCpu, remainingBudget);

            selectedGpu = selectGraphicsCard(remainingBudget);
            remainingBudget = subtractPrice(selectedGpu, remainingBudget);
        }

        // 2. Selecting Motherboard
        selectedMotherboard = selectMotherboard(selectedCpu, remainingBudget);
        remainingBudget = subtractPrice(selectedMotherboard, remainingBudget);

        // 3. Selecting RAM
        selectedRam = selectRam(selectedMotherboard, remainingBudget);
        remainingBudget = subtractPrice(selectedRam, remainingBudget);

        // 4. Selecting CPU Cooler
        selectedCpuCooler = selectCpuCooler(selectedCpu, remainingBudget);
        remainingBudget = subtractPrice(selectedCpuCooler, remainingBudget);

        // 5. Selecting Power Supply
        selectedPowerSupply = selectPowerSupply(selectedCpu, selectedGpu, remainingBudget);
        remainingBudget = subtractPrice(selectedPowerSupply, remainingBudget);

        // 6. Selecting Storage
        selectedStorage = selectStorage(remainingBudget);
        remainingBudget = subtractPrice(selectedStorage, remainingBudget);

        // 7. Selecting Case
        selectedCase = selectCase(selectedMotherboard, remainingBudget);
        remainingBudget = subtractPrice(selectedCase, remainingBudget);

        // Ensuring all components are selected
        if (anyComponentMissing(selectedCpu, selectedGpu, selectedMotherboard, selectedRam, selectedStorage, selectedPowerSupply, selectedCase, selectedCpuCooler)) {
            log.warn("Could not generate a complete build within the budget.");
            return Optional.empty();
        }

        // Creating and saving the build
        UserBuild build = createUserBuild(userId, selectedCpu, selectedGpu, selectedMotherboard, selectedRam,
                selectedStorage, selectedPowerSupply, selectedCase, selectedCpuCooler,
                budget.subtract(remainingBudget), usageType);

        log.info("Build successfully generated: {}", build);
        return Optional.of(build);
    }

    private GraphicsCard selectGraphicsCard(BigDecimal budget) {
        List<GraphicsCard> options = graphicsCardRepository.findGraphicsCardsUnderBudget(budget);
        return options.stream().findFirst().orElse(null);
    }

    private Processor selectProcessor(BigDecimal budget, UsageTypesEnum usageType) {
        List<Processor> options = processorRepository.findProcessorsUnderBudget(budget, usageType.toString());
        return options.stream().findFirst().orElse(null);
    }

    private Motherboard selectMotherboard(Processor cpu, BigDecimal budget) {
        if (cpu == null) return null;
        List<Motherboard> options = motherboardRepository.findCompatibleMotherboardsUnderBudget(cpu.getSocket().getSocketId(), budget);
        return options.stream().findFirst().orElse(null);
    }

    private RamModule selectRam(Motherboard motherboard, BigDecimal budget) {
        if (motherboard == null) return null;
        List<RamModule> options = ramModuleRepository.findCompatibleRamModulesUnderBudget(motherboard.getSupportedDdr(), budget);
        return options.stream().findFirst().orElse(null);
    }

    private CpuCooler selectCpuCooler(Processor cpu, BigDecimal budget) {
        if (cpu == null) return null;
        List<CpuCooler> options = cpuCoolerRepository.findCompatibleCoolersUnderBudget(cpu.getSocket().getSocketId(), budget);
        return options.stream().findFirst().orElse(null);
    }

    private PowerSupply selectPowerSupply(Processor cpu, GraphicsCard gpu, BigDecimal budget) {
        if (cpu == null || gpu == null) return null;
        int requiredPower = calculatePowerConsumption(cpu, gpu);
        List<PowerSupply> options = powerSupplyRepository.findPowerSuppliesUnderBudget(requiredPower, budget);
        return options.stream().findFirst().orElse(null);
    }

    private Storage selectStorage(BigDecimal budget) {
        List<Storage> options = storageRepository.findBestStorageUnderBudget(budget);
        return options.stream().findFirst().orElse(null);
    }

    private Case selectCase(Motherboard motherboard, BigDecimal budget) {
        if (motherboard == null) return null;
        List<Case> options = caseRepository.findCasesUnderBudget(budget);
        return options.stream().findFirst().orElse(null);
    }

    private BigDecimal subtractPrice(Component component, BigDecimal budget) {
        if (component == null) return budget;
        return budget.subtract(component.getPrice());
    }

    private boolean anyComponentMissing(Component... components) {
        return Arrays.stream(components).anyMatch(Objects::isNull);
    }

    private UserBuild createUserBuild(Long userId, Processor cpu, GraphicsCard gpu, Motherboard motherboard, RamModule ram,
                                      Storage storage, PowerSupply powerSupply, Case pcCase, CpuCooler cpuCooler,
                                      BigDecimal totalPrice, UsageTypesEnum usageType) {
        UserBuild build = new UserBuild();

        build.setCpu(cpu);
        build.setGpu(gpu);
        build.setMotherboard(motherboard);
        build.setRam(ram);
        build.setStorage(storage);
        build.setPowerSupply(powerSupply);
        build.setPcCase(pcCase);
        build.setCpuCooler(cpuCooler);
        build.setTotalPrice(totalPrice);
        build.setUsageType(usageType);

        if (userId != null) {
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            build.setUser(user);
            createUserBuild(build);
        }

        return build;
    }

    /**
     * Calculates the total power consumption of a CPU and GPU.
     *
     * @param selectedCpu The selected CPU.
     * @param selectedGpu The selected GPU.
     * @return The total power consumption in watts.
     */
    private static int calculatePowerConsumption(Processor selectedCpu, GraphicsCard selectedGpu) {
        if (selectedCpu == null || selectedGpu == null) {
            throw new IllegalArgumentException("CPU and GPU cannot be null.");
        }

        int cpuPower = selectedCpu.getPowerConsumption();
        int gpuPower = selectedGpu.getPowerConsumption();

        return cpuPower + gpuPower;
    }

}
