package br.com.java.pcbuild.repositories;

import br.com.java.pcbuild.models.entities.ProcessorSocketCompatibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessorSocketCompatibilityRepository extends JpaRepository<ProcessorSocketCompatibility, Integer> {



}
