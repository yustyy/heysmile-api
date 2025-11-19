package com.heysmile.heysmileapi.api.controllers;

import com.heysmile.heysmileapi.business.abstracts.CalendarService;
import com.heysmile.heysmileapi.business.abstracts.UserService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/calendars")
public class CalendarController {

    private final CalendarService calendarService;
    private final UserService userService;

    public CalendarController(CalendarService calendarService, UserService userService) {
        this.calendarService = calendarService;
        this.userService = userService;
    }

}