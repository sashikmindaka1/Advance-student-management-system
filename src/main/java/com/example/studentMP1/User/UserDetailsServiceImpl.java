package com.example.studentMP1.User;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Transient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired // implement user repository
    UserRepository userRepository;

    @Override
    @Transactional // get correct roles from database
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // ensure if there user in database this email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // if found user return user using build method in userDetailsImpl
        return UserDetailsImpl.build(user);
    }
}
