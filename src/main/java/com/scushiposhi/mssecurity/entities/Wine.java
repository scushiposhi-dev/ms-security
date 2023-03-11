package com.scushiposhi.mssecurity.entities;

import com.scushiposhi.mssecurity.utils.EntityUtils.*;
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
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_wine"
    )
    @SequenceGenerator(
            name = "seq_wine",
            allocationSize = 5
    )
    private Long id;

    private String name;
    @Column(name = "year_month")
    private YearMonth yearMonth;
    private String winery;
    @Enumerated(EnumType.STRING)
    private WineTypeEnum wineTypeEnum;

}
