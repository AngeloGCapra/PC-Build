package br.com.java.pcbuild.controllers;

import br.com.java.pcbuild.models.entities.Socket;
import br.com.java.pcbuild.services.SocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/socket")
public class SocketController {

    private final SocketService socketService;

    @GetMapping(value = "/getAllSockets")
    public ResponseEntity<List<Socket>> getAllSockets() {
        List<Socket> sockets = socketService.findAllSockets();
        return ResponseEntity.ok(sockets);
    }

    @GetMapping("/getSocketById/{socketId}")
    public ResponseEntity<Optional<Socket>> getSocketById(@PathVariable("socketId") Long socketId) {
        Optional<Socket> socket = socketService.findSocketById(socketId);
        return ResponseEntity.ok(socket);
    }

    @PostMapping(value = "/createSocket")
    public ResponseEntity<Socket> createSocket(@RequestBody Socket socket) {
        Socket newSocket = socketService.createSocket(socket);
        return new ResponseEntity<>(newSocket, HttpStatus.CREATED);
    }

    @PutMapping("/updateSocket/{socketId}")
    public ResponseEntity<Socket> updateSocket(@PathVariable("socketId") Long socketId,
                                               @RequestBody Socket updatedSocket) {
        Socket socket = socketService.updateSocket(socketId, updatedSocket);
        return ResponseEntity.ok(socket);
    }

    @DeleteMapping("/deleteSocket/{socketId}")
    public ResponseEntity<Void> deleteSocket(@PathVariable("socketId") Long socketId) {
        socketService.deleteSocket(socketId);
        return ResponseEntity.noContent().build();
    }

}
