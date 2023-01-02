package com.gmail.neo960211.sportradartask.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Venue {
    @Id
    private String id;
    @NotBlank(message = "Venue: name cannot be empty value!")
    private String name;
    @Min(1)
    private int capacity;
    @NotBlank(message = "Venue: city name cannot be empty value!")
    private String city_name;
    @NotBlank(message = "Venue: country name cannot be empty value!")
    private String country_name;

    private String map_coordinates;
    @NotBlank(message = "Venue: country code cannot be empty value!")
    private String country_code;
    @OneToMany(mappedBy = "venue", targetEntity = Event.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Event> events = new HashSet<>();
}
