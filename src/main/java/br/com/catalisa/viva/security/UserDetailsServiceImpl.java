package br.com.catalisa.viva.security;

import br.com.catalisa.viva.model.usuario.UsuarioModel;
import br.com.catalisa.viva.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioModel usuarioModel = usuarioRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
        return new User(usuarioModel.getUsername(), usuarioModel.getPassword(), true, true, true, true, usuarioModel.getAuthorities());
    }
}

