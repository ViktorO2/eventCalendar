package com.example.eventCalendar.service;

import com.example.eventCalendar.model.Event;
import com.example.eventCalendar.repository.EventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    public List<Event> getAllEvents() {

//        List<Event> events = eventRepository.findAll();
//        // Format date in each event
//        events.forEach(event -> event.setDueDate(formatLocalDate(event.getDueDate())));
//        return events;
        // return eventRepository.findAll();

        return eventRepository.findAllByOrderByPriorityAsc();
    }

//    public Event getEventById(Long eventId) {
//        return eventRepository.findById(eventId).orElse(null);
//    }

    public void addEvent(Event event) {
        eventRepository.save(event);
    }

    public Optional<Event> getById(Long id) {
        return eventRepository.findById(id);
    }

    public void updateEvent(Event updatedEvent) {
        eventRepository.save(updatedEvent);
    }

    public void deleteEventById(Long id) {
        eventRepository.deleteById(id);
    }

}
