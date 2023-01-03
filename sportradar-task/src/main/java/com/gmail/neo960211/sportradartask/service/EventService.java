package com.gmail.neo960211.sportradartask.service;

import com.gmail.neo960211.sportradartask.model.Event;
import com.gmail.neo960211.sportradartask.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> sortList() {
        List<Event> list = (List<Event>) eventRepository.findAll();
        list.sort(new Comparator<Event>() {
            @Override
            public int compare(Event e1, Event e2) {
                if (compareProbabilities(e1) < compareProbabilities(e2)) {
                    return 1;
                } else if (compareProbabilities(e1) > compareProbabilities(e2)) {
                    return -1;
                }
                return 0;
            }
        });
        return list;
    }

    public static double compareProbabilities(Event e) {
        if (e.getProbability_home_team_winner() > e.getProbability_away_team_winner()
                && e.getProbability_home_team_winner() > e.getProbability_draw()) {
            return e.getProbability_home_team_winner();
        } else if (e.getProbability_away_team_winner() > e.getProbability_home_team_winner()
                && e.getProbability_away_team_winner() > e.getProbability_draw()) {
            return e.getProbability_away_team_winner();
        } else {
            return e.getProbability_draw();
        }
    }

    public Iterable<Event> list() {
        return eventRepository.findAll();
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public void save(List<Event> events) {
        eventRepository.saveAll(events);
    }
}
