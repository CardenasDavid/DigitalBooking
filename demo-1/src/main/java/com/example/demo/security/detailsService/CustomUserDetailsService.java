package com.example.demo.security.detailsService;


import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Usuario;
import com.example.demo.payload.request.UserCity;
import com.example.demo.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario user=  usuarioRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User Not Found with email: " + email));

        return CustomUserDetails.build(user);
    }

    public void updateCity(long id, UserCity userCity) throws NotFoundException {
        Usuario usuario=usuarioRepository.findById(id).orElseThrow(()->new NotFoundException("Usuario no encontrado"));
        usuario.setCiudad(userCity.getCiudad());
        usuarioRepository.save(usuario);
    }
}
