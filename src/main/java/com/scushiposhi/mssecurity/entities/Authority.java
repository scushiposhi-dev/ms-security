package com.scushiposhi.mssecurity.entities;

import com.scushiposhi.mssecurity.utils.EntityUtils.WineAuthoritiesEnum;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authority")
public class Authority {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_authority"
    )
    @SequenceGenerator(
            name = "seq_authority",
            allocationSize = 5
    )
    private Long id;
    @Enumerated(EnumType.STRING)
    private WineAuthoritiesEnum permission;

    @ManyToMany(mappedBy = "authorities")
    private Set<Role> roles;

}
