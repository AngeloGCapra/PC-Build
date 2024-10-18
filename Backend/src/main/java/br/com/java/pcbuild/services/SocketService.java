package br.com.java.pcbuild.services;

import br.com.java.pcbuild.models.entities.Socket;
import br.com.java.pcbuild.repositories.SocketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SocketService {

    private final ModelMapper modelMapper;
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
                .map(socketEntity -> {
                    modelMapper.map(updatedSocket, socketEntity);
                    return socketRepository.save(socketEntity);
                }).orElseThrow(() -> new RuntimeException("Socket not found"));
    }

    public void deleteSocket(Integer socketId) {
        socketRepository.deleteById(socketId);
    }

}
