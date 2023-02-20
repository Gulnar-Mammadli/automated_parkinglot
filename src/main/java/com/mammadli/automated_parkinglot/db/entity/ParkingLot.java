package com.mammadli.automated_parkinglot.db.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "parkingLots")
public class ParkingLot {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(
            name = "uuid2",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column( nullable = false,columnDefinition = "VARCHAR(255)")

    private String id;

    private BigDecimal price;
}
