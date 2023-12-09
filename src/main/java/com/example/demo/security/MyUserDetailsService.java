package com.example.demo.security;


import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFound;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByName(username);

        if (user == null) {
            throw new UserNotFound("User not found!");
        }

        MyUserDetails userDetails = new MyUserDetails(user.getMail(),user.getPassword(), user.getIsActive() );

        return userDetails;
    }
}
