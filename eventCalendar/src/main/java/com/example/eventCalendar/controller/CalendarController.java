package com.example.eventCalendar.controller;

import com.example.eventCalendar.model.Event;
import com.example.eventCalendar.model.EventDTO;
import com.example.eventCalendar.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
//@RequestMapping("/events")
public class CalendarController {
    @Autowired
    private EventService eventService;
    @GetMapping("/")
    public String showEvents(Model model) {
        List<Event> events = eventService.getAllEvents();
        List<EventDTO> eventsDTO = events.stream()
                .map(event -> new EventDTO(event.getId(), event.getTitle(), event.getDueDate(), event.getThemeColor()))
                .collect(Collectors.toList());

        model.addAttribute("eventsJson", eventsDTO);
        return "home";
    }

    @GetMapping("/list")
    public String showEventList(Model model) {
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "/list";
    }

    @GetMapping("/add")
    public String showAddEventForm(Model model) {
        model.addAttribute("event", new Event());
        return "add";
    }

    @PostMapping("/add")
    public String addEvent(@Valid @ModelAttribute("event") Event event, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("custom  Error","All fields must be filled");
            return "/add";
        }
        eventService.addEvent(event);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String showEditEventForm(@RequestParam Long id, Model model) {
        Optional<Event> event = eventService.getById(id);
        if (event.isPresent()) {
            model.addAttribute("event", event.get());
            model.addAttribute("eventId", id);
            return "edit";
        }else {
            return "redirect:/";
            }
    }

    @PostMapping("/edit")
    public String editEventSubmission(@ModelAttribute("event") Event updatedEvent) {
        eventService.updateEvent(updatedEvent);
        return "redirect:/";
    }


    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable("id") Long id) {
        eventService.deleteEventById(id);
        return "redirect:/";
    }

    @GetMapping("/details")
    public String showEventDetails(@RequestParam Long id, Model model) {
        Optional<Event> event = eventService.getById(id);
        if (event.isPresent()) {
            model.addAttribute("event", event.get());
            model.addAttribute("eventId", id);
            return "details";
        } else {
            return "redirect:/";
        }
    }
}