package com.gmail.neo960211.sportradartask.model;

import jakarta.persistence.*;
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
public class Competitor {
    @Id
    private String id;
    @NotBlank(message = "Competitor: name cannot be empty value!")
    @Column(unique = true)
    private String name;
    @NotBlank(message = "Competitor: country cannot be empty value!")
    private String country;
    @NotBlank(message = "Competitor: country code cannot be empty value!")
    private String country_code;
    @NotBlank(message = "Competitor: abbreviation cannot be empty value!")
    private String abbreviation;
    @NotBlank(message = "Competitor: gender cannot be empty value!")
    private Gender gender;
    @OneToMany(mappedBy = "homeCompetitor", targetEntity = Event.class, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Event> homeEvent = new HashSet<>();
    @OneToMany(mappedBy = "awayCompetitor", targetEntity = Event.class, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Event> awayEvent = new HashSet<>();
}
