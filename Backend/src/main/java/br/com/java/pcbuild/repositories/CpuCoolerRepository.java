package br.com.java.pcbuild.repositories;

import br.com.java.pcbuild.models.entities.CpuCooler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CpuCoolerRepository extends JpaRepository<CpuCooler, Long> {

    List<CpuCooler> findAllByOrderByPriceAsc();

    @Query("""
            SELECT cc
            FROM CpuCooler cc
            JOIN CpuCoolerSocketCompatibility csc ON csc.cpuCooler = cc
            WHERE csc.socket.socketId = :socketId
                AND cc.price <= :budget
            ORDER BY cc.componentRanking DESC
            """)
    List<CpuCooler> findCompatibleCoolersUnderBudget(@Param("socketId") Long socketId,
                                                     @Param("budget") BigDecimal budget);

}
