package com.example.eventCalendar.controller;

import com.example.eventCalendar.model.Event;
import com.example.eventCalendar.model.EventDTO;
import com.example.eventCalendar.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/events")
public class CalendarController {

    private final EventService eventService;

    @Autowired
    public CalendarController(EventService eventService) {
        this.eventService = eventService;
    }

   /* @GetMapping("/")
    public String showEvents(Model model) {
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("eventsJson", events);
       // model.addAttribute("events", events);
        return "home";
    }*/
    @GetMapping("/")
   public String showEvents(Model model) {
       List<Event> events = eventService.getAllEvents();

       List<EventDTO> eventsDTO = events.stream()
               .map(event -> new EventDTO(event.getTitle(), event.getDueDate()))
               .collect(Collectors.toList());

       model.addAttribute("eventsJson", eventsDTO);
       return "home";
   }

    @GetMapping("/list")
    public String showEventList(Model model) {
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "list";
    }

    @GetMapping("/add")
    public String showAddEventForm(Model model) {
        model.addAttribute("event", new Event());
        return "add";
    }

    @PostMapping("/add")
    public String addEvent(@ModelAttribute("event") Event event) {
        eventService.addEvent(event);
        return "redirect:/events/list";
    }

    @GetMapping("/edit/{id}")
    public String editEventForm(@PathVariable Long id, Model model) {
        Event event = eventService.getEventById(id);
        if (event != null) {
            model.addAttribute("event", event);
            return "edit";
        } else {
            return "redirect:/events/list";
        }
    }

    @PostMapping("/edit/{id}")
    public String editEvent(@PathVariable Long id, @ModelAttribute("event") Event updatedEvent) {
        Event existingEvent = eventService.getEventById(id);
        if (existingEvent != null) {
            existingEvent.setTitle(updatedEvent.getTitle());
            existingEvent.setDescription(updatedEvent.getDescription());
            existingEvent.setDueDate(updatedEvent.getDueDate());
            existingEvent.setPriority(updatedEvent.getPriority());
            eventService.updateEvent(existingEvent);
        }
        return "redirect:/events/list";
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable("id") Long id) {
        eventService.deleteEventById(id);
        return "redirect:/events/list";
    }
}
