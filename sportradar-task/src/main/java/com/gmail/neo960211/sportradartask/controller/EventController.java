package com.gmail.neo960211.sportradartask.controller;

import com.gmail.neo960211.sportradartask.service.EventService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventController {

    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostConstruct
    private void loadData() {
        eventService.sortList();
    }

    @RequestMapping("/events")
    public String start(Model model) {
        model.addAttribute("events", eventService.selectNumberOfEvents(eventService.sortList(), 10));
        return "events";
    }
}
