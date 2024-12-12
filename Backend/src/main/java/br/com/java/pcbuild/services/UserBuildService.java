package br.com.java.pcbuild.services;

import br.com.java.pcbuild.enums.UsageTypesEnum;
import br.com.java.pcbuild.models.entities.*;
import br.com.java.pcbuild.repositories.*;
import br.com.java.pcbuild.utils.BuildNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

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

    public Optional<UserBuild> generateBuildWithOptimization(Long userId, BigDecimal budget, UsageTypesEnum usageType) {
        log.info("Generating optimized build: User: {}, Budget: {}, Usage Type: {}", userId, budget, usageType);

        PriorityQueue<BuildNode> queue = new PriorityQueue<>(Comparator.comparing(BuildNode::getTotalCost).reversed());

        if (UsageTypesEnum.ENTERTAINMENT.equals(usageType)) {
            List<GraphicsCard> gpuOptions = graphicsCardRepository.findGraphicsCardsUnderBudget(budget);

            for (GraphicsCard gpu : gpuOptions) {
                BuildNode initialNode = new BuildNode(budget);

                initialNode.setGpu(gpu);
                initialNode.setRemainingBudget(budget.subtract(gpu.getPrice()));
                initialNode.setTotalCost(gpu.getPrice());

                queue.add(initialNode);
            }
        } else if (UsageTypesEnum.WORK.equals(usageType)) {
            List<Processor> cpuOptions = processorRepository.findProcessorsUnderBudget(usageType.toString(), budget);

            for (Processor cpu : cpuOptions) {
                BuildNode initialNode = new BuildNode(budget);

                initialNode.setCpu(cpu);
                initialNode.setRemainingBudget(budget.subtract(cpu.getPrice()));
                initialNode.setTotalCost(cpu.getPrice());

                queue.add(initialNode);
            }
        }

        UserBuild bestBuild = null;

        while (!queue.isEmpty()) {
            BuildNode currentNode = queue.poll();

            UserBuild build = tryGenerateBuild(currentNode, userId, usageType);
            if (build != null) {
                bestBuild = build;
                break;
            }
        }

        if (bestBuild == null) {
            log.warn("Could not generate a complete build within the budget.");
            return Optional.empty();
        }

        log.info("Optimized build successfully generated: {}", bestBuild);
        return Optional.of(bestBuild);
    }

    private UserBuild tryGenerateBuild(BuildNode node, Long userId, UsageTypesEnum usageType) {
        if (UsageTypesEnum.ENTERTAINMENT.equals(usageType)) {
            if (node.getCpu() == null) {
                List<Processor> processors = processorRepository.findProcessorsUnderBudget(usageType.toString(), node.getRemainingBudget());

                if (!processors.isEmpty()) {
                    Processor selectedProcessor = processors.getFirst();

                    node.setCpu(selectedProcessor);
                    node.setRemainingBudget(node.getRemainingBudget().subtract(selectedProcessor.getPrice()));
                    node.setTotalCost(node.getTotalCost().add(selectedProcessor.getPrice()));
                }
            }
        } else if (UsageTypesEnum.WORK.equals(usageType)) {
            if (node.getGpu() == null) {
                List<GraphicsCard> graphicsCards = graphicsCardRepository.findGraphicsCardsUnderBudget(node.getRemainingBudget());

                if (!graphicsCards.isEmpty()) {
                    GraphicsCard selectedGraphicsCard = graphicsCards.getFirst();

                    node.setGpu(selectedGraphicsCard);
                    node.setRemainingBudget(node.getRemainingBudget().subtract(selectedGraphicsCard.getPrice()));
                    node.setTotalCost(node.getTotalCost().add(selectedGraphicsCard.getPrice()));
                }
            }
        }

        if (node.getMotherboard() == null && node.getCpu() != null) {
            List<Motherboard> motherboards = motherboardRepository.findCompatibleMotherboardsUnderBudget(node.getCpu().getSocket().getSocketId(), node.getRemainingBudget());

            if (!motherboards.isEmpty()) {
                Motherboard selectedMotherboard = motherboards.getFirst();

                node.setMotherboard(selectedMotherboard);
                node.setRemainingBudget(node.getRemainingBudget().subtract(selectedMotherboard.getPrice()));
                node.setTotalCost(node.getTotalCost().add(selectedMotherboard.getPrice()));
            }
        }

        if (node.getRam() == null && node.getMotherboard() != null) {
            List<RamModule> ramModules = ramModuleRepository.findCompatibleRamModulesUnderBudget(node.getMotherboard().getSupportedDdr(), node.getRemainingBudget());

            if (!ramModules.isEmpty()) {
                RamModule selectedRam = ramModules.getFirst();

                node.setRam(selectedRam);
                node.setRemainingBudget(node.getRemainingBudget().subtract(selectedRam.getPrice()));
                node.setTotalCost(node.getTotalCost().add(selectedRam.getPrice()));
            }
        }

        if (node.getCpuCooler() == null && node.getMotherboard() != null) {
            List<CpuCooler> coolers = cpuCoolerRepository.findCompatibleCoolersUnderBudget(node.getMotherboard().getSocket().getSocketId(), node.getRemainingBudget());

            if (!coolers.isEmpty()) {
                CpuCooler selectedCooler = coolers.getFirst();

                node.setCpuCooler(selectedCooler);
                node.setRemainingBudget(node.getRemainingBudget().subtract(selectedCooler.getPrice()));
                node.setTotalCost(node.getTotalCost().add(selectedCooler.getPrice()));
            }
        }

        if (node.getPowerSupply() == null && node.getCpu() != null && node.getGpu() != null) {
            int requiredWattage = calculatePowerConsumption(node.getCpu(), node.getGpu());
            List<PowerSupply> powerSupplies = powerSupplyRepository.findPowerSuppliesUnderBudget(requiredWattage, node.getRemainingBudget());

            if (!powerSupplies.isEmpty()) {
                PowerSupply selectedPowerSupply = powerSupplies.getFirst();

                node.setPowerSupply(selectedPowerSupply);
                node.setRemainingBudget(node.getRemainingBudget().subtract(selectedPowerSupply.getPrice()));
                node.setTotalCost(node.getTotalCost().add(selectedPowerSupply.getPrice()));
            }
        }

        if (node.getStorage() == null) {
            List<Storage> storages = storageRepository.findStorageUnderBudget(node.getRemainingBudget());

            if (!storages.isEmpty()) {
                Storage selectedStorage = storages.getFirst();

                node.setStorage(selectedStorage);
                node.setRemainingBudget(node.getRemainingBudget().subtract(selectedStorage.getPrice()));
                node.setTotalCost(node.getTotalCost().add(selectedStorage.getPrice()));
            }
        }

        if (node.getPcCase() == null) {
            List<Case> cases = caseRepository.findCasesUnderBudget(node.getRemainingBudget());

            if (!cases.isEmpty()) {
                Case selectedCase = cases.getFirst();

                node.setPcCase(selectedCase);
                node.setRemainingBudget(node.getRemainingBudget().subtract(selectedCase.getPrice()));
                node.setTotalCost(node.getTotalCost().add(selectedCase.getPrice()));
            }
        }

        if (node.isComplete()) {
            return createUserBuild(
                    userId,
                    node.getCpu(),
                    node.getGpu(),
                    node.getMotherboard(),
                    node.getRam(),
                    node.getCpuCooler(),
                    node.getPowerSupply(),
                    node.getStorage(),
                    node.getPcCase(),
                    node.getTotalCost(),
                    usageType);
        } else {
            return null;
        }
    }

    private UserBuild createUserBuild(Long userId, Processor cpu, GraphicsCard gpu, Motherboard motherboard, RamModule ram,
                                      CpuCooler cpuCooler, PowerSupply powerSupply, Storage storage, Case pcCase,
                                      BigDecimal totalPrice, UsageTypesEnum usageType) {
        UserBuild build = new UserBuild();

        build.setCpu(cpu);
        build.setGpu(gpu);
        build.setMotherboard(motherboard);
        build.setRam(ram);
        build.setCpuCooler(cpuCooler);
        build.setPowerSupply(powerSupply);
        build.setStorage(storage);
        build.setPcCase(pcCase);
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
