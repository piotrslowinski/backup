package com.piotrslowinski.ui.controllers;

import com.piotrslowinski.api.UrlService;
import com.piotrslowinski.api.UserService;
import com.piotrslowinski.model.UrlAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private UrlService urlService;

    @Value("${server.port}")
    private String port;

    @Value("${server.address}")
    private String address;

    @Autowired
    public UserController(UserService userService, UrlService urlService) {
        this.userService = userService;
        this.urlService = urlService;
    }

    @PostMapping
    public void saveUser(@RequestParam String firstName, @RequestParam String lastName) {
        userService.createUser(firstName, lastName);
    }

    @GetMapping("/{userId}")
    public UrlAddress getUrl(@PathVariable Long userId, @RequestParam Long surveyId) {
        return urlService.createUniqueSurveyUrl(userId, surveyId);
    }

}
