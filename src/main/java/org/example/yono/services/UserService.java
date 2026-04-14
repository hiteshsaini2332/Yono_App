package org.example.yono.services;
import org.example.yono.model.Customer;
import org.example.yono.repositories.CustomerRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    CustomerRepo r;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer c= r.findByEmail(email);
        if(c==null){
            throw new UsernameNotFoundException("User not found");
        }

        return new User(c.getEmail(),c.getPassword(),new ArrayList<>());

    }
}

