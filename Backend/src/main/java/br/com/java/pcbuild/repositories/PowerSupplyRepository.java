package br.com.java.pcbuild.repositories;

import br.com.java.pcbuild.models.entities.PowerSupply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PowerSupplyRepository extends JpaRepository<PowerSupply, Integer> {



}
