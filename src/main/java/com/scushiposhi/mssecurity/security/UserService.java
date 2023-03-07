package com.scushiposhi.mssecurity.security;

import com.scushiposhi.mssecurity.entities.Authority;
import com.scushiposhi.mssecurity.entities.User;
import com.scushiposhi.mssecurity.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException("This damn user does not exist:"+username);
        }
        User user = userOptional.get();
        return new org.springframework.security.core.userdetails
                .User(user.getUsername(),user.getPassword(),user.getEnabled(),
                user.getAccountNonExpired(),user.getCredentialsNonExpired(),user.getAccountNonLocked(),
                convertToSpringGrantedAuthorities(user.getAuthorities()));
    }

    private Collection<? extends GrantedAuthority> convertToSpringGrantedAuthorities(Set<Authority> authorities) {
        Set<GrantedAuthority> collect = authorities
                .stream()
                .map(Authority::getRole)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
        return collect;
    }
}
