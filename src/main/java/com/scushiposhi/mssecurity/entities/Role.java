package com.scushiposhi.mssecurity.entities;

import com.scushiposhi.mssecurity.utils.EntityUtils.*;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_role")
    @SequenceGenerator(name = "seq_role", allocationSize = 5)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RoleEnum name;
    @Singular
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "role_authority",
            joinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "id")})
    private Set<Authority> authorities;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
