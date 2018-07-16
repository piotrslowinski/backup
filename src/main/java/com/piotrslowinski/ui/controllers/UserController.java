package com.piotrslowinski.ui.controllers;

import com.piotrslowinski.api.UrlService;
import com.piotrslowinski.api.UserService;
import com.piotrslowinski.model.SurveyUrlAddress;
import com.piotrslowinski.model.UrlAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

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

//    @GetMapping("/{userId}")
//    public UrlAddress getUrl(@PathVariable Long userId, @RequestParam Long surveyId) {
//        return urlService.createUniqueSurveyUrl(userId, surveyId);
//    }

//    @PostMapping("/{userId}")
//    public void exportUserToFile(@PathVariable Long userId) {
//        userService.exportUserToFile(userId);
//    }

    @GetMapping("/{userId}")
    public void exportUserToFile(@PathVariable Long userId, HttpServletResponse response) throws IOException {
        byte[] csvData = userService.createFileWitUser(userId);
        String fileName = "Report.csv";
        response.setContentType("application/csv");
        response.setHeader("Content-disposition", "attachment; filename=report.csv");
        response.setContentLength(csvData.length);
        response.setContentLength(csvData.length);
        response.getOutputStream().write(csvData);
        response.getOutputStream().flush();
    }

//    @GetMapping("/{userId}")
//    public void exportWithFileWriter(@PathVariable Long userId) {
//        userService.exportWithFileWriter(userId);
//    }

//    @GetMapping("/{userId}")
//    public void doGet(@PathVariable Long userId, HttpServletResponse response, HttpServletRequest request) {
//        byte[] csvData = userService.createFileWitUser(userId);
//        response.setContentType("text/csv");
//        response.setHeader("Content-Disposition", "attachment; filename=\"users.csv\"");
//        try {
//            OutputStream outputStream = response.getOutputStream();
//            outputStream.write(csvData);
//            outputStream.flush();
//            outputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
