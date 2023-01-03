package com.gmail.neo960211.sportradartask.service;

import com.gmail.neo960211.sportradartask.model.Event;
import com.gmail.neo960211.sportradartask.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> sortList() {
        List<Event> list = (List<Event>) eventRepository.findAll();
        return list;
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
