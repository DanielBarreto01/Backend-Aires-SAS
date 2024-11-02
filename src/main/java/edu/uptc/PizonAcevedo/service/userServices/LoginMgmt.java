package edu.uptc.PizonAcevedo.service.userServices;

import edu.uptc.PizonAcevedo.domain.model.userModel.Credential;
import edu.uptc.PizonAcevedo.domain.repository.repositoryUser.CredentialRepository;
import edu.uptc.PizonAcevedo.domain.repository.repositoryUser.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class LoginMgmt implements UserDetailsService {

    @Autowired
    CredentialRepository credentialRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         Credential credential = credentialRepository.findByUserName(username)
                    .orElseThrow(() -> new UsernameNotFoundException("El ususario" + username + " No existe"));
        Collection<? extends GrantedAuthority> authorities = userRepository.findUserByCredential(credential)
                .getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getName().name())))
                .collect(Collectors.toSet());
        return new User(credential.getUserName(),
                credential.getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }
}
