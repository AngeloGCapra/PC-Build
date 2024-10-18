package br.com.java.pcbuild.repositories;

import br.com.java.pcbuild.models.entities.GraphicsCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphicsCardRepository extends JpaRepository<GraphicsCard, Integer> {



}
