package com.gmail.neo960211.sportradartask.controller;

import com.gmail.neo960211.sportradartask.service.CompetitorService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CompetitorController {
    private CompetitorService competitorService;

    public CompetitorController(CompetitorService competitorService) {
        this.competitorService = competitorService;
    }

    @PostConstruct
    private void loadData() {
        competitorService.sortList();
    }

    @RequestMapping("/competitors")
    public String start(Model model) {
        model.addAttribute("competitors", competitorService.sortList());
        return "competitors";
    }
}
