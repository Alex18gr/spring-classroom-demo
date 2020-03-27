package io.alexc.classroomdemo.controller;

import io.alexc.classroomdemo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class UpdatesController {

    private final SimpMessagingTemplate messagingTemplate;

    public UpdatesController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

}
