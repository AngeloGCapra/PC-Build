package br.com.java.pcbuild.services;

import br.com.java.pcbuild.models.entities.Socket;
import br.com.java.pcbuild.repositories.SocketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SocketService {

    private final SocketRepository socketRepository;

    public List<Socket> findAllSockets() {
        return socketRepository.findAll();
    }

    public Optional<Socket> findSocketById(Integer socketId) {
        return socketRepository.findById(socketId);
    }

    public Socket createSocket(Socket socket) {
        return socketRepository.save(socket);
    }

    public Socket updateSocket(Integer socketId, Socket updatedSocket) {
        return socketRepository.findById(socketId)
                .map(socket -> {
                    socket.setName(updatedSocket.getName());

                    return socketRepository.save(socket);
                }).orElseThrow(() -> new RuntimeException("Socket not found"));
    }

    public void deleteSocket(Integer socketId) {
        socketRepository.deleteById(socketId);
    }

}
