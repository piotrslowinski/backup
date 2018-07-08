package com.piotrslowinski.ui.controllers;

import com.piotrslowinski.api.UrlService;
import com.piotrslowinski.api.UserService;
import com.piotrslowinski.model.UrlAddress;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private UrlService urlService;

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
