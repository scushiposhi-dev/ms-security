package com.scushiposhi.mssecurity.bootstrap;

import com.scushiposhi.mssecurity.entities.Authority;
import com.scushiposhi.mssecurity.entities.User;
import com.scushiposhi.mssecurity.repo.AuthorityRepository;
import com.scushiposhi.mssecurity.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserDataLoader implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) {
        if (authorityRepository.count() == 0) {
            loadSecurityData();
        }

    }

    private void loadSecurityData() {
        Authority admin = authorityRepository.save(Authority.builder().role("ADMIN").build());
        Authority devOps = authorityRepository.save(Authority.builder().role("DEVOPS").build());
        Authority developer = authorityRepository.save(Authority.builder().role("DEVELOPER").build());
        Authority customer = authorityRepository.save(Authority.builder().role("CUSTOMER").build());

        User polsi = userRepository.save(User.builder().username("POLSI").password(encoder.encode("123test123")).authorities(Set.of(admin, developer, devOps)).build());
        User luca = userRepository.save(User.builder().username("LUCA").password(encoder.encode("provaProvata")).authority(devOps).build());
        User matteo = userRepository.save(User.builder().username("MATTEO").password(encoder.encode("prova123test123Provata")).authority(customer).build());

        log.info("Authorities loaded:{}", authorityRepository.count());
        log.info("Users loaded:{}", userRepository.count());
    }
}
