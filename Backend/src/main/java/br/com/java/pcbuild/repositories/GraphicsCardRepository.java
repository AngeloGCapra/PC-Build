package br.com.java.pcbuild.repositories;

import br.com.java.pcbuild.models.entities.GraphicsCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface GraphicsCardRepository extends JpaRepository<GraphicsCard, Long> {

    @Query("""
            SELECT gp.gpu
            FROM GpuPerformance gp
            WHERE gp.gpu.price <= :budget
            ORDER BY gp.relativePerformance DESC
            """)
    List<GraphicsCard> findGraphicsCardsUnderBudget(@Param("budget") BigDecimal budget);

    @Query("""
            SELECT gp.gpu
            FROM GpuPerformance gp
            WHERE gp.gpu.price < :price
            ORDER BY gp.relativePerformance DESC
            """)
    List<GraphicsCard> findCheaperGraphicsCards(BigDecimal price);
}
