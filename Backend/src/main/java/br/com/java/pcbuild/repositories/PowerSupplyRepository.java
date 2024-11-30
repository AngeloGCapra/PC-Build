package br.com.java.pcbuild.repositories;

import br.com.java.pcbuild.models.entities.PowerSupply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PowerSupplyRepository extends JpaRepository<PowerSupply, Long> {

    @Query("""
            SELECT ps
            FROM PowerSupply ps
            WHERE ps.wattage >= :requiredWattage
                AND ps.price <= :budget
            ORDER BY ps.componentRanking DESC
            """)
    List<PowerSupply> findPowerSuppliesUnderBudget(@Param("requiredWattage") int requiredWattage,
                                                   @Param("budget") BigDecimal budget);

}
