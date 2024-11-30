package br.com.java.pcbuild.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data

@Entity
@Table(name = "sockets")
public class Socket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "socket_id")
    private Long socketId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

}
