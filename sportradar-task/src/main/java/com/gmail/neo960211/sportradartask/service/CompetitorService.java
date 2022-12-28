package com.gmail.neo960211.sportradartask.service;

import com.gmail.neo960211.sportradartask.model.Competitor;
import com.gmail.neo960211.sportradartask.repository.CompetitorRepository;

import java.util.List;

public class CompetitorService {
    private final CompetitorRepository competitorRepository;

    public CompetitorService(CompetitorRepository competitorRepository) {
        this.competitorRepository = competitorRepository;
    }

    public Iterable<Competitor> list() {
        return competitorRepository.findAll();
    }

    public Competitor save(Competitor competitor) {
        return competitorRepository.save(competitor);
    }

    public void save(List<Competitor> competitors) {
        competitorRepository.saveAll(competitors);
    }
}
