package com.scushiposhi.mssecurity.entities;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String password;

    @Column
    private  String username;
    @Singular
    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "USER_ID",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "AUTHORITY_ID",referencedColumnName = "id"))
    private Set<Authority> authorities;
    @Builder.Default
    private Boolean accountNonExpired=true;
    @Builder.Default
    private Boolean accountNonLocked=true;
    @Builder.Default
    private Boolean credentialsNonExpired=true;
    @Builder.Default
    private Boolean enabled=true;

}
