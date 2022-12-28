package com.gmail.neo960211.sportradartask.controller;

import com.gmail.neo960211.sportradartask.model.Competitor;
import com.gmail.neo960211.sportradartask.service.CompetitorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/competitors")
public class CompetitorController {
    private CompetitorService competitorService;

    public CompetitorController(CompetitorService competitorService) {
        this.competitorService = competitorService;
    }

    @GetMapping("/list")
    public Iterable<Competitor> list() {
        return competitorService.list();
    }
}
