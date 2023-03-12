package com.scushiposhi.mssecurity.bootstrap;

import com.scushiposhi.mssecurity.entities.Authority;
import com.scushiposhi.mssecurity.entities.Role;
import com.scushiposhi.mssecurity.entities.User;
import com.scushiposhi.mssecurity.entities.Wine;
import com.scushiposhi.mssecurity.repositories.WineRepository;
import com.scushiposhi.mssecurity.repositories.security.AuthorityRepository;
import com.scushiposhi.mssecurity.repositories.security.RoleRepository;
import com.scushiposhi.mssecurity.repositories.security.UserRepository;
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

import static com.scushiposhi.mssecurity.utils.EntityUtils.RoleEnum.*;
import static com.scushiposhi.mssecurity.utils.EntityUtils.WineAuthoritiesEnum.*;
import static com.scushiposhi.mssecurity.utils.EntityUtils.WineTypeEnum.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserDataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
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
        Wine friulano = Wine.builder().wineTypeEnum(WHITE).winery("Vigne di Zamo").name("friulano").yearMonth(YearMonth.of(2020, Month.APRIL)).build();
        Wine rosso = Wine.builder().wineTypeEnum(RED).winery("Colle Duga").name("merlot").yearMonth(YearMonth.of(2018, Month.NOVEMBER)).build();
        Wine sparkling = Wine.builder().wineTypeEnum(SPARKLING).winery("Franciacorta").name("Franciacorta Satèn").yearMonth(YearMonth.of(2021, Month.AUGUST)).build();
        Wine rose = Wine.builder().wineTypeEnum(ROSE).winery("Franciacorta").name("Franciacorta Rosé").yearMonth(YearMonth.of(2022, Month.JUNE)).build();
        List<Wine> wines = wineRepository.saveAll(Arrays.asList(friulano, rose, sparkling, rosso));

        log.info("Wines loaded:{}",wineRepository.count());
    }

    private void loadSecurityData() {
        // IF YOU WANT TO WORK WITH hasRole and hasAnyRole remember to chage the role adding prefix 'ROLE_'
        Authority readWine = authorityRepository.save(Authority.builder().permission(WINE_READ).build());
        Authority createWine = authorityRepository.save(Authority.builder().permission(WINE_CREATE).build());
        Authority updateWine = authorityRepository.save(Authority.builder().permission(WINE_UPDATE).build());
        Authority deleteWine = authorityRepository.save(Authority.builder().permission(WINE_DELETE).build());
        Authority userPageRead = authorityRepository.save(Authority.builder().permission(USER_PAGE_READ).build());
        Authority adminPageRead = authorityRepository.save(Authority.builder().permission(ADMIN_PAGE_READ).build());

        Role adminRole = roleRepository.save(Role.builder()
                .name(ROLE_ADMIN).build());
        Role devOpsRole = roleRepository.save(Role.builder()
                .name(ROLE_DEVOPS).build());
        Role developerRole = roleRepository.save(Role.builder()
                .name(ROLE_DEV).build());
        Role testerRole = roleRepository.save(Role.builder()
                .name(ROLE_TESTER).build());
        Role customerRole = roleRepository.save(Role.builder()
                .name(ROLE_CUSTOMER).build());

        adminRole.setAuthorities(Set.of(readWine,createWine,updateWine,deleteWine,adminPageRead));
        devOpsRole.setAuthorities(Set.of(readWine));
        developerRole.setAuthorities(Set.of(readWine,createWine,updateWine,deleteWine));
        testerRole.setAuthorities(Set.of(readWine,updateWine,userPageRead));
        customerRole.setAuthorities(Set.of(readWine,userPageRead));
        roleRepository.saveAll(Set.of(adminRole,developerRole,devOpsRole,testerRole,customerRole));

        User polsi = userRepository.save(
                User.builder()
                        .username("POLSI")
                        .password(encoder.encode("123test123"))
                        //.role(adminRole)
                        .build());
        polsi.setRoles(Set.of(adminRole,developerRole,devOpsRole));
        User luca = userRepository.save(
                User.builder().
                        username("LUCA").
                        password(encoder.encode("provaProvata"))
                        //.role(testerRole)
                        .build());
        luca.setRoles(Set.of(testerRole));

        User matteo = userRepository.save(
                User.builder().
                        username("MATTEO")
                        .password(encoder.encode("prova123test123Provata"))
                        //.role(customerRole)
                        .build());
        matteo.setRoles(Set.of(customerRole));

        userRepository.saveAll(Set.of(polsi,luca,matteo));

        log.info("Roles loaded:{}", roleRepository.count());
        log.info("Authorities loaded:{}", authorityRepository.count());
        log.info("Users loaded:{}", userRepository.count());
    }
}
