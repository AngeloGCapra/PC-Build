package br.com.java.pcbuild.repositories;

import br.com.java.pcbuild.enums.RamModuleTypeEnum;
import br.com.java.pcbuild.models.entities.RamModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RamModuleRepository extends JpaRepository<RamModule, Long> {

    @Query("""
            SELECT rm
            FROM RamModule rm
            WHERE rm.type = :ramModuleType
                AND rm.price <= :budget
            ORDER BY rm.componentRanking DESC
            """)
    List<RamModule> findCompatibleRamModulesUnderBudget(@Param(value = "ramModuleType") RamModuleTypeEnum ramModuleType,
                                                        @Param(value = "budget") BigDecimal budget);

}
