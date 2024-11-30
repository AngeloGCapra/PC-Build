package br.com.java.pcbuild.repositories;

import br.com.java.pcbuild.models.entities.Motherboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface MotherboardRepository extends JpaRepository<Motherboard, Long> {

    @Query("""
            SELECT m
            FROM Motherboard m
            WHERE m.socket.socketId = :socketId
                AND m.price <= :budget
            ORDER BY m.componentRanking DESC
            """)
    List<Motherboard> findCompatibleMotherboardsUnderBudget(@Param("socketId") Long socketId,
                                                            @Param("budget") BigDecimal budget);

}
