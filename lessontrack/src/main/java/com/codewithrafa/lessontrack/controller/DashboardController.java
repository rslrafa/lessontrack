package com.codewithrafa.lessontrack.controller;

import com.codewithrafa.lessontrack.repository.AssessmentRepository;
import com.codewithrafa.lessontrack.repository.LessonRepository;
import com.codewithrafa.lessontrack.repository.SubjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class DashboardController {

    private final SubjectRepository subjectRepository;
    private final LessonRepository lessonRepository;
    private final AssessmentRepository assessmentRepository;

    public DashboardController(
            SubjectRepository subjectRepository,
            LessonRepository lessonRepository,
            AssessmentRepository assessmentRepository
    ) {
        this.subjectRepository = subjectRepository;
        this.lessonRepository = lessonRepository;
        this.assessmentRepository = assessmentRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        LocalDate today = LocalDate.now();
        LocalDate endOfWeek = today.plusDays(7);

        model.addAttribute("totalSubjects", subjectRepository.count());
        model.addAttribute("totalLessons", lessonRepository.count());
        model.addAttribute("completedLessons", lessonRepository.countByStatus("Completed"));
        model.addAttribute("upcomingAssessments", assessmentRepository.findByDueDateAfterOrderByDueDateAsc(today));
        model.addAttribute("lessonsThisWeek", lessonRepository.findByLessonDateBetween(today, endOfWeek));
        model.addAttribute("pendingAssessments", assessmentRepository.countByStatus("Upcoming"));

        return "dashboard";
    }
}