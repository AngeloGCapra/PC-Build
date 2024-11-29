package br.com.java.pcbuild.repositories;

import br.com.java.pcbuild.models.entities.UserBuild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBuildRepository extends JpaRepository<UserBuild, Long> {


    
}
