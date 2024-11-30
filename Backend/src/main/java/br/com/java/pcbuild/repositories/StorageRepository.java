package br.com.java.pcbuild.repositories;

import br.com.java.pcbuild.models.entities.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {

    @Query("""
            SELECT s
            FROM Storage s
            WHERE s.price <= :budget
            ORDER BY s.componentRanking DESC
            """)
    List<Storage> findBestStorageUnderBudget(@Param("budget") BigDecimal budget);

}
