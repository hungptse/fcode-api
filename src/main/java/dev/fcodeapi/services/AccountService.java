package dev.fcodeapi.services;

import dev.fcodeapi.entities.AccountEntity;
import dev.fcodeapi.entities.RoleEntity;
import dev.fcodeapi.repositories.AccountRepository;
import dev.fcodeapi.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository ar;

    @Autowired
    private RoleRepository rr;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEntity ae = ar.findByStudentId(username);
        if (ae == null){
            throw new UsernameNotFoundException(username);
        }
        List<GrantedAuthority> list = new ArrayList<>();
        GrantedAuthority ga = new SimpleGrantedAuthority("ROLE_" + ae.getRole().getRoleName());
        list.add(ga);
        return new User(ae.getStudentId(),ae.getPassword(), list);
    }
}
