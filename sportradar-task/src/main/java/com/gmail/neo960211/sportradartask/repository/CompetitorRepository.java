package com.gmail.neo960211.sportradartask.repository;

import com.gmail.neo960211.sportradartask.model.Competitor;
import org.springframework.data.repository.CrudRepository;

public interface CompetitorRepository extends CrudRepository<Competitor, String> {
    public Competitor findAllById(String id);
}
