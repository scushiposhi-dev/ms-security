package com.scushiposhi.mssecurity.entities;

import lombok.*;

import javax.persistence.*;
import java.time.YearMonth;

@Entity
@Setter@Getter
@Builder
@Table(name = "wine")
@NoArgsConstructor
@AllArgsConstructor
public class Wine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @Column(name = "year_month")
    private YearMonth yearMonth;
    private String winery;
    @Enumerated(EnumType.STRING)
    private WineTypeEnum wineTypeEnum;

}
