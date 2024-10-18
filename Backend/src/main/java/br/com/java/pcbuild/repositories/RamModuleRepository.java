package br.com.java.pcbuild.repositories;

import br.com.java.pcbuild.models.entities.RamModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RamModuleRepository extends JpaRepository<RamModule, Integer> {
}
