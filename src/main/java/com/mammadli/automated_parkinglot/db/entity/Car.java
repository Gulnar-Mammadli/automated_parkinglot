package com.mammadli.automated_parkinglot.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(
            name = "uuid2",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column( nullable = false,columnDefinition = "VARCHAR(255)")
    private  String id;

    private int height;

    private int weight;

    private String floorId;

    private LocalDateTime parkingTime;

    private LocalDateTime unparkingTime;
}
