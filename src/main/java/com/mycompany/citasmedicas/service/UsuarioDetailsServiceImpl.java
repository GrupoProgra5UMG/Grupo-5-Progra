package com.mycompany.citasmedicas.service;

import com.mycompany.citasmedicas.model.Usuario;
import com.mycompany.citasmedicas.repository.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + correo));

       List<GrantedAuthority> authorities = usuario.getRoles().stream()
        .<GrantedAuthority>map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
        .toList();

        return new org.springframework.security.core.userdetails.User(
                usuario.getCorreo(),
                usuario.getPassword(),
                usuario.isActivo(),
                true, true, true,
                authorities
        );
    }
}