package com.scushiposhi.mssecurity.entities;


import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

import static com.scushiposhi.mssecurity.utils.NpeSafeUtilities.npeSafeStream;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_user"
    )
    @SequenceGenerator(
            name = "seq_user",
            allocationSize = 5
    )
    private Long id;
    private String password;

    private String username;
    @Singular
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "id"))
    Set<Role> roles;

    @Transient
    private Set<Authority> authorities;

    @Builder.Default
    private Boolean accountNonExpired = true;

    @Builder.Default
    private Boolean accountNonLocked = true;
    @Builder.Default
    private Boolean credentialsNonExpired = true;
    @Builder.Default
    private Boolean enabled = true;

    public Set<Authority> getAuthorities() {
        return npeSafeStream(this.roles)
                .map(Role::getAuthorities)
                .flatMap(Set::stream).collect(Collectors.toSet());
    }
}
