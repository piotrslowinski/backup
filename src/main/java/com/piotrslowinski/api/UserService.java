package com.piotrslowinski.api;

import com.piotrslowinski.model.User;
import com.piotrslowinski.model.exporter.ReportExporter;
import com.piotrslowinski.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserService {

    private UserRepository userRepository;
    private ReportExporter reportExporter;

    @Autowired
    public UserService(UserRepository userRepository, ReportExporter reportExporter) {
        this.userRepository = userRepository;
        this.reportExporter = reportExporter;
    }


    @Transactional
    public void createUser(String firstName, String lastName) {
        userRepository.save(new User(firstName, lastName));
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).get();
    }

    public void exportUserToFile(Long userId) {
        User user = getUser(userId);
        reportExporter.exportToFile(user);
    }

    public byte[] createFileWitUser(Long userId) {
        User user = getUser(userId);
        return reportExporter.createFile(user);
    }

}
