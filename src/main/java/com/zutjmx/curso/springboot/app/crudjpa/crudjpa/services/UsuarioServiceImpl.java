package com.zutjmx.curso.springboot.app.crudjpa.crudjpa.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zutjmx.curso.springboot.app.crudjpa.crudjpa.entities.Role;
import com.zutjmx.curso.springboot.app.crudjpa.crudjpa.entities.Usuario;
import com.zutjmx.curso.springboot.app.crudjpa.crudjpa.repositories.RoleRepository;
import com.zutjmx.curso.springboot.app.crudjpa.crudjpa.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        Optional<Role> roleOptionalUser = roleRepository.findByNombre("ROLE_USER");
        List<Role> roles = new ArrayList<>();

        roleOptionalUser.ifPresent(roles::add);
        
        if (usuario.isAdmin()) {
            Optional<Role> roleOptionalAdmin = roleRepository.findByNombre("ROLE_ADMIN");
            roleOptionalAdmin.ifPresent(roles::add);
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setRoles(roles);

        return usuarioRepository.save(usuario);
    }

}
