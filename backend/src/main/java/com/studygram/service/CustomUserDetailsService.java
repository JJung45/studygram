package com.studygram.service;

import com.studygram.domain.User;
import com.studygram.mapper.UserMapper;
import com.studygram.utils.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User member = userMapper.findByUserId(username);
        if (member == null) {
            throw new UsernameNotFoundException("Can not find username.");
        }
        return UserPrincipal.create(member);
    }
}
