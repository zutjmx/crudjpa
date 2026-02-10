package com.zutjmx.curso.springboot.app.crudjpa.crudjpa.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zutjmx.curso.springboot.app.crudjpa.crudjpa.entities.Usuario;
import com.zutjmx.curso.springboot.app.crudjpa.crudjpa.repositories.UsuarioRepository;

@Service
public class JpaUsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByUsername(username);
        if (optionalUsuario.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Usuario no encontrado: %s", username));            
        }

        Usuario usuario = optionalUsuario.orElseThrow();

        List<GrantedAuthority> authorities = usuario.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getNombre()))
            .collect(Collectors.toList());

        /* return User.builder()
            .username(usuario.getUsername())
            .password(usuario.getPassword())
            .authorities(authorities)
            .build(); */

        return new User(
            usuario.getUsername(), 
            usuario.getPassword(),
            usuario.isEnabled(),
            true, // accountNonExpired
            true, // credentialsNonExpired,
            true, // accountNonLocked 
            authorities
        );

    }

}
