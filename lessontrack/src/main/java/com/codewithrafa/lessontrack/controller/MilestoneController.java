package com.codewithrafa.lessontrack.controller;

import com.codewithrafa.lessontrack.repository.AssessmentRepository;
import com.codewithrafa.lessontrack.repository.LessonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class MilestoneController {

    private final LessonRepository lessonRepository;
    private final AssessmentRepository assessmentRepository;

    public MilestoneController(LessonRepository lessonRepository,
                               AssessmentRepository assessmentRepository) {
        this.lessonRepository = lessonRepository;
        this.assessmentRepository = assessmentRepository;
    }

    @GetMapping("/milestones")
    public String milestones(Model model) {
        LocalDate today = LocalDate.now();
        LocalDate nextMonth = today.plusDays(30);

        model.addAttribute("upcomingLessons",
                           lessonRepository.findByLessonDateBetween(today, nextMonth));

        model.addAttribute("upcomingAssessments",
                           assessmentRepository.findByDueDateAfterOrderByDueDateAsc(today));

        return "milestones";
    }
}