package br.com.java.pcbuild.repositories;

import br.com.java.pcbuild.models.entities.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseRepository extends JpaRepository<Case, Integer> {



}
