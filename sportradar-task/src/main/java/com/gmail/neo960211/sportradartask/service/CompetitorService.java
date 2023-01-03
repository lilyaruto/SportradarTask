package com.gmail.neo960211.sportradartask.service;

import com.gmail.neo960211.sportradartask.model.Competitor;
import com.gmail.neo960211.sportradartask.repository.CompetitorRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CompetitorService {
    private final CompetitorRepository competitorRepository;

    public CompetitorService(CompetitorRepository competitorRepository) {
        this.competitorRepository = competitorRepository;
    }

    public Iterable<Competitor> list() {
        return competitorRepository.findAll();
    }

    public List<Competitor> sortList() {
        List<Competitor> list = (List<Competitor>) competitorRepository.findAll();
        list.sort(Comparator.comparing(Competitor::getName));
        return list;
    }

    public Competitor save(Competitor competitor) {
        return competitorRepository.save(competitor);
    }

    public void save(List<Competitor> competitors) {
        competitorRepository.saveAll(competitors);
    }
}
