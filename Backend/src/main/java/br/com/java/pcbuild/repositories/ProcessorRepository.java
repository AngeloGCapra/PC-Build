package br.com.java.pcbuild.repositories;

import br.com.java.pcbuild.models.entities.Processor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProcessorRepository extends JpaRepository<Processor, Long> {

    List<Processor> findAllByOrderByPriceAsc();

    @Query("""
            SELECT cp.cpu
            FROM CpuPerformance cp
            WHERE cp.cpu.price <= :budget
            ORDER BY
                CASE
                    WHEN :usageType = 'GAMES' THEN cp.gamePerformance
                    WHEN :usageType = 'WORK' THEN cp.multiThreadPerformance
                    ELSE cp.singleThreadPerformance
                END DESC
            """)
    List<Processor> findProcessorsUnderBudget(@Param("usageType") String usageType,
                                              @Param("budget") BigDecimal budget);

}
