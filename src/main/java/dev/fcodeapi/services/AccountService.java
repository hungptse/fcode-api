package dev.fcodeapi.services;

import dev.fcodeapi.entities.AccountEntity;
import dev.fcodeapi.repositories.AccountRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AccountService implements UserDetailsService {

    private AccountRepository ar;

    public AccountService(AccountRepository ar) {
        this.ar = ar;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEntity ae = ar.findByStudentId(username);
        if (ae == null){
            throw new UsernameNotFoundException(username);
        }
        return new User(ae.getStudentId(),ae.getPassword(), Collections.emptyList());
    }
}
