package br.com.java.pcbuild.repositories;

import br.com.java.pcbuild.models.entities.CpuCooler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CpuCoolerRepository extends JpaRepository<CpuCooler, Integer> {



}
