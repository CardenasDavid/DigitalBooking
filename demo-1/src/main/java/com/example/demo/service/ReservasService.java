package com.example.demo.service;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Cliente;
import com.example.demo.model.Reserva_x_usuario;
import com.example.demo.model.Reservas;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.repository.ReservasRepository;
import com.example.demo.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservasService {

    private final ReservasRepository repository;
    private final ClienteRepository clienteRepository;

    private final UsuarioRepository usuarioRepository;

    private final ProductoRepository productoRepository;

    public List<Reservas> list(){
        return repository.findAll();
    }
    public void add(Reserva_x_usuario reservasXUsuario) throws Exception {

        var producto = productoRepository.findById(reservasXUsuario.getIdProducto()).orElseThrow(()-> new Exception());
        var usuarioR =  usuarioRepository.findById(reservasXUsuario.getIdUsuario()).orElseThrow(()-> new Exception());
/*        var cliente = clienteRepository.findByUsuario(usuarioRepository.getReferenceById(reservasXUsuario.getIdUsuario())).orElseThrow(()-> new Exception());*/

        Cliente clienteNuevo = new Cliente(usuarioRepository.getReferenceById(reservasXUsuario.getIdUsuario()));

   /*     if (clienteRepository.existsById(reservasXUsuario.getIdUsuario())) {*/

        Optional<Cliente> clienteExistente = clienteRepository.findById(reservasXUsuario.getIdUsuario());

        if (clienteRepository.existsByUsuarioId(reservasXUsuario.getIdUsuario())) {
            Reservas reservas = new Reservas(
                    reservasXUsuario.getHoraReserva(),
                    reservasXUsuario.getFechaDesde(),
                    reservasXUsuario.getFechaHasta(),
                    productoRepository.getReferenceById(reservasXUsuario.getIdProducto()),
                   clienteRepository.getIdByUsuarioId(reservasXUsuario.getIdUsuario())
            );
            repository.save(reservas);
        } else {
            Cliente nuevoCliente = clienteRepository.save(clienteNuevo);

            Reservas reservas = new Reservas(
                    reservasXUsuario.getHoraReserva(),
                    reservasXUsuario.getFechaDesde(),
                    reservasXUsuario.getFechaHasta(),
                    productoRepository.getReferenceById(reservasXUsuario.getIdProducto()),
                    nuevoCliente
            );

            repository.save(reservas);
        };

    }
    public void update(Reservas reservas){ repository.save(reservas);
    }
    public void deleteById(Long id) throws NotFoundException {
        repository.findById(id).orElseThrow(()->new NotFoundException("Reserva no encontrada") );
        repository.deleteById(id);}
    public Reservas getById(Long id){ return repository.getReferenceById(id);}

    public List<Reservas> listByUsuarioId(Long usuarioId){
        return repository.findByClienteUsuarioId(usuarioId);
    }
}
