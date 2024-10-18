package br.com.java.pcbuild.repositories;

import br.com.java.pcbuild.models.entities.Motherboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotherboardRepository extends JpaRepository<Motherboard, Integer> {



}
