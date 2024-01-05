package com.example.eventCalendar.model;

import java.time.LocalDate;

public class EventDTO {
    private Long id;
    private String title;
    private LocalDate start;

    private String themeColor;


    public EventDTO(Long id, String title, LocalDate start, String themeColor) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.themeColor = themeColor;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getThemeColor() {
        return themeColor;
    }

    public void setThemeColor(String themeColor) {
        this.themeColor = themeColor;
    }
}

