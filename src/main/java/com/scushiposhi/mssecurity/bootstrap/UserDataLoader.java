package com.scushiposhi.mssecurity.bootstrap;

import com.scushiposhi.mssecurity.entities.Authority;
import com.scushiposhi.mssecurity.entities.User;
import com.scushiposhi.mssecurity.entities.Wine;
import com.scushiposhi.mssecurity.entities.WineTypeEnum;
import com.scushiposhi.mssecurity.repositories.AuthorityRepository;
import com.scushiposhi.mssecurity.repositories.UserRepository;
import com.scushiposhi.mssecurity.repositories.WineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Month;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserDataLoader implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final WineRepository wineRepository;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) {
        if (authorityRepository.count() == 0) {
            loadSecurityData();
            loadWines();
        }

    }

    private void loadWines() {
        Wine friulano = Wine.builder().wineTypeEnum(WineTypeEnum.WHITE).winery("Vigne di Zamo").name("friulano").yearMonth(YearMonth.of(2020, Month.APRIL)).build();
        Wine rosso = Wine.builder().wineTypeEnum(WineTypeEnum.RED).winery("Colle Duga").name("merlot").yearMonth(YearMonth.of(2018, Month.NOVEMBER)).build();
        Wine sparkling = Wine.builder().wineTypeEnum(WineTypeEnum.SPARKLING).winery("Franciacorta").name("Franciacorta Satèn").yearMonth(YearMonth.of(2021, Month.AUGUST)).build();
        Wine rose = Wine.builder().wineTypeEnum(WineTypeEnum.SPARKLING).winery("Franciacorta").name("Franciacorta Rosé").yearMonth(YearMonth.of(2022, Month.JUNE)).build();
        List<Wine> wines = wineRepository.saveAll(Arrays.asList(friulano, rose, sparkling, rose));

        log.info("Wines loaded:{}",wineRepository.count());
    }

    private void loadSecurityData() {
        // IF YOU WANT TO WORK WITH hasRole and hasAnyRole remember to chage the role adding prefix 'ROLE_'
        Authority admin = authorityRepository.save(Authority.builder().role("ROLE_ADMIN").build());
        Authority devOps = authorityRepository.save(Authority.builder().role("ROLE_DEVOPS").build());
        Authority developer = authorityRepository.save(Authority.builder().role("ROLE_DEVELOPER").build());
        Authority customer = authorityRepository.save(Authority.builder().role("ROLE_CUSTOMER").build());
        Authority user = authorityRepository.save(Authority.builder().role("ROLE_USER").build());

        User polsi = userRepository.save(
                User.builder()
                        .username("POLSI")
                        .password(encoder.encode("123test123"))
                        .authorities(Set.of(admin, developer, devOps))
                        .build());
        User luca = userRepository.save(
                User.builder().
                        username("LUCA").
                        password(encoder.encode("provaProvata"))
                        .authorities(Set.of(customer,user))
                        .build());
        User matteo = userRepository.save(
                User.builder().
                        username("MATTEO")
                        .password(encoder.encode("prova123test123Provata"))
                        .authority(customer)
                        .build());

        log.info("Authorities loaded:{}", authorityRepository.count());
        log.info("Users loaded:{}", userRepository.count());
    }
}
