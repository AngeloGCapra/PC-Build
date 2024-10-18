package br.com.java.pcbuild.repositories;

import br.com.java.pcbuild.models.entities.CpuPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CpuPerformanceRepository extends JpaRepository<CpuPerformance, Integer> {



}
