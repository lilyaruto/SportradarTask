package com.gmail.neo960211.sportradartask.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Event {
    @Id
    private String sport_event_id;
    @NotNull(message = "Event: start date cannot be empty value!")
    private LocalDateTime start_date;
    @NotBlank(message = "Event: sport name cannot be empty value!")
    private String sport_name;
    @NotBlank(message = "Event: competition name cannot be empty value!")
    private String competition_name;
    @NotNull
    private String competition_id;
    @NotBlank(message = "Event: season name cannot be empty value!")
    private String season_name;
    @Min(0)
    @Max(100)
    private double probability_home_team_winner;
    @Min(0)
    @Max(100)
    private double probability_draw;
    @Min(0)
    @Max(100)
    private double probability_away_team_winner;
    @ManyToOne(optional = false, fetch = FetchType.LAZY, targetEntity = Venue.class)
    @JoinColumn(nullable = false, name = "venue")
    private Venue venue;
    @ManyToOne(optional = false, fetch = FetchType.LAZY, targetEntity = Competitor.class)
    @JoinColumn(nullable = false, name = "home_competitor")
    private Competitor homeCompetitor;
    @ManyToOne(optional = false, fetch = FetchType.LAZY, targetEntity = Competitor.class)
    @JoinColumn(nullable = false, name = "away_competitor")
    private Competitor awayCompetitor;
}
