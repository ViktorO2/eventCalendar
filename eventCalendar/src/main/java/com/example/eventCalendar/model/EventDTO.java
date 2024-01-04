package com.example.eventCalendar.model;

import java.time.LocalDate;

public class EventDTO {
    private String title;
    private LocalDate start; // Може да използвате LocalDateTime за Java 8 и по-нови версии

    // Конструктор, гетъри и сетъри


    public EventDTO(String title, LocalDate start) {
        this.title = title;
        this.start = start;
    }

    public EventDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }
}

