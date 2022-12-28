package com.gmail.neo960211.sportradartask.service;

import com.gmail.neo960211.sportradartask.model.Venue;
import com.gmail.neo960211.sportradartask.repository.VenueRepository;

import java.util.List;

public class VenueService {
    private final VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    public Iterable<Venue> list() {
        return venueRepository.findAll();
    }

    public Venue save(Venue venue) {
        return venueRepository.save(venue);
    }

    public void save(List<Venue> venues) {
        venueRepository.saveAll(venues);
    }
}
