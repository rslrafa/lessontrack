package com.codewithrafa.lessontrack.controller;

import com.codewithrafa.lessontrack.entity.Assessment;
import com.codewithrafa.lessontrack.service.AssessmentService;
import com.codewithrafa.lessontrack.service.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AssessmentController {

    private final AssessmentService assessmentService;
    private final SubjectService subjectService;

    public AssessmentController(AssessmentService assessmentService, SubjectService subjectService) {
        this.assessmentService = assessmentService;
        this.subjectService = subjectService;
    }

    @GetMapping("/assessments")
    public String listAssessments(Model model) {
        model.addAttribute("assessments", assessmentService.getAllAssessments());
        return "assessments";
    }

    @GetMapping("/assessments/new")
    public String showCreateForm(Model model) {
        model.addAttribute("assessment", new Assessment());
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "assessment-form";
    }

    @PostMapping("/assessments")
    public String saveAssessment(@ModelAttribute("assessment") Assessment assessment) {
        assessmentService.saveAssessment(assessment);
        return "redirect:/assessments";
    }

    @GetMapping("/assessments/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("assessment", assessmentService.getAssessmentById(id));
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "assessment-form";
    }

    @GetMapping("/assessments/delete/{id}")
    public String deleteAssessment(@PathVariable Long id) {
        assessmentService.deleteAssessment(id);
        return "redirect:/assessments";
    }
}