package br.com.java.pcbuild.repositories;

import br.com.java.pcbuild.models.entities.CpuCoolerSocketCompatibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CpuCoolerSocketCompatibilityRepository extends JpaRepository<CpuCoolerSocketCompatibility, Integer> {



}
