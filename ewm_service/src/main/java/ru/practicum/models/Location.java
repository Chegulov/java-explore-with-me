package ru.practicum.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "locations")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float lat;
    private float lon;
}
