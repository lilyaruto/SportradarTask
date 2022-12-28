package com.gmail.neo960211.sportradartask.service;

import com.gmail.neo960211.sportradartask.model.Event;
import com.gmail.neo960211.sportradartask.repository.EventRepository;

import java.util.List;

public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
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
