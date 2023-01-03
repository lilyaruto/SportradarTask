package com.gmail.neo960211.sportradartask.repository;

import com.gmail.neo960211.sportradartask.model.Venue;
import org.springframework.data.repository.CrudRepository;

public interface VenueRepository extends CrudRepository<Venue, Long> {
    public Venue findAllById(String id);
}
