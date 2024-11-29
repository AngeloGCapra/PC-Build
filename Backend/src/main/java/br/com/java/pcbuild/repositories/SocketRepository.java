package br.com.java.pcbuild.repositories;

import br.com.java.pcbuild.models.entities.Socket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocketRepository extends JpaRepository<Socket, Long> {



}
