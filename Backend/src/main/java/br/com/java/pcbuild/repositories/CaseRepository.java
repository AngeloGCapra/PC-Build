package br.com.java.pcbuild.repositories;

import br.com.java.pcbuild.models.entities.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CaseRepository extends JpaRepository<Case, Long> {

    List<Case> findAllByOrderByPriceAsc();

    @Query("""
            SELECT c
            FROM Case c
            WHERE c.price <= :budget
            ORDER BY c.componentRanking DESC
            """)
    List<Case> findCasesUnderBudget(@Param("budget") BigDecimal budget);

}
