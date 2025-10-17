package com.myapp.task.controller;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentController {

    @GetMapping("/upload")
    public String upload() {
        return "Upload endpoint accessed by UPLOADER";
    }

    @GetMapping("/review")
    public String review() {
        return "Review endpoint accessed by REVIEWER";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Admin endpoint accessed by ADMIN";
    }
}

