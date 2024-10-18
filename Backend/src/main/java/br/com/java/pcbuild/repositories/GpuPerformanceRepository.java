package br.com.java.pcbuild.repositories;

import br.com.java.pcbuild.models.entities.GpuPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GpuPerformanceRepository extends JpaRepository<GpuPerformance, Integer> {



}
